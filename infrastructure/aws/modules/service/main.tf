variable "aws_region" {
  description = "The AWS region to deploy the infrastructure to."
  default     = "us-east-1"
}

variable "service_name" {
  description = "The name of the ECS service."
}

variable "task_definition" {
  description = "The name of the task definition to use for the ECS service."
}

variable "database_name" {
  description = "The name of the PostgreSQL database."
}

variable "database_username" {
  description = "The username to use for the PostgreSQL database."
}

variable "database_password" {
  description = "The password to use for the PostgreSQL database."
}

resource "aws_default_vpc" "default" {
  tags = {
    Name = "Default VPC"
  }
}

resource "aws_security_group" "main" {
    name = "secgrp"

}

resource "aws_subnet" "main" {
  vpc_id     = aws_default_vpc.default.id
  cidr_block = "10.0.1.0/24"

  tags = {
    Name = "Main"
  }
}

resource "aws_lb" "main" {
  name               = "load-balancer"
  internal           = false
  load_balancer_type = "application"
  security_groups    = [aws_security_group.main.id]
  subnets            = [aws_subnet.main.id]

  tags = merge(
    {
      Name = "${var.service_name}-lb-app_main"
    }
  )
}

resource "aws_lb_target_group" "main" {
  name        = "lb-target-group"
  port        = 80
  protocol    = "HTTP"
  vpc_id      = aws_default_vpc.default.id
  target_type = "ip"

  health_check {
    protocol = "HTTP"
    matcher  = "200-202"
    path     = "/health"
  }

  depends_on = [
    aws_lb.main,
  ]
}

resource "aws_ecs_task_definition" "ecs_task_definition" {
  family                   = var.task_definition
  container_definitions    = jsonencode([
    {
      name      = "${var.service_name}-container"
      image     = "your-container-image"
      cpu       = 256
      memory    = 512
      essential = true
      portMappings = [
        {
          containerPort = 80
          hostPort      = 80
        },
        {
          containerPort = 443
          hostPort      = 443
        },
      ],
      environment = [
        {
          name  = "DATABASE_URL"
          value = "postgres://${var.database_username}:${var.database_password}@${aws_rds_cluster_instance.database.endpoint}/${var.database_name}"
        },
      ]
    },
  ])
}

resource "aws_ecs_service" "ecs_service" {
  name            = var.service_name
  cluster         = "default"
  task_definition = aws_ecs_task_definition.ecs_task_definition.arn

  network_configuration {
    subnets = ["your-subnets"]
    security_groups = ["your-security-groups"]
    assign_public_ip = true
  }

  load_balancer {
    target_group_arn = aws_lb_target_group.main.arn
    container_name   = "${var.service_name}-container"
    container_port   = 80
  }
}

resource "aws_rds_cluster" "database" {
  engine             = "postgres"
  engine_version     = "14.6"
  database_name      = var.database_name
  master_username    = var.database_username
  master_password    = var.database_password
  cluster_identifier = "${var.service_name}-rds-cluster"
  db_cluster_instance_class = "db.t2.micro"
  availability_zones = ["${var.aws_region}a", "${var.aws_region}b", "${var.aws_region}c"]

  db_subnet_group_name = "your-db-subnet-group"

  tags = {
    Name = "${var.service_name}-rds-cluster"
  }
}

resource "aws_rds_cluster_instance" "database" {
  identifier         = "${var.service_name}-rds-instance"
  cluster_identifier = aws_rds_cluster.database.id
  instance_class     = "db.t2.micro"

  tags = {
    Name = "${var.service_name}-rds-instance"
  }
}
