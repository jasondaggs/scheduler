package com.electrodna.scheduler.service

import com.electrodna.scheduler.model.Task
import com.electrodna.scheduler.repository.TaskRepository
import org.springframework.stereotype.Service

@Service
class TaskService  (val taskRepository : TaskRepository)
{
    fun createTask( task: Task) : Task =
        taskRepository.save(task);

    fun fetchAllTasks(): Iterable<Task> =
        taskRepository.findAll();
}