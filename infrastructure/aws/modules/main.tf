terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 4.16"
    }
  }
  required_version = ">= 1.2.0"
}

provider "aws" {
  region  = "us-east-1"
}

module "ecs_service_with_rds" {
  source           = "./service"
  aws_region       = "us-east-1"
  service_name     = "scheduler"
  task_definition  = "scheduler-definition"
  database_name    = "scheduler"
  database_username = "scheduler_user"
  database_password = "scheduler_password"
}