package com.electrodna.scheduler.controller

import com.electrodna.scheduler.model.Task
import com.electrodna.scheduler.service.TaskService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping


@Controller
@RequestMapping(name = "/api/v1/task")
class TaskController( val taskService : TaskService) {
    @GetMapping
    fun fetchAll() : Iterable<Task> =
        taskService.fetchAllTasks()
}