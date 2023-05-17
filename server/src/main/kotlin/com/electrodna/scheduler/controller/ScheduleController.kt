package com.electrodna.scheduler.controller

import com.electrodna.scheduler.failure
import com.electrodna.scheduler.success
import com.electrodna.scheduler.model.Schedule
import com.electrodna.scheduler.service.ScheduleService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import java.util.*


@Controller
@RequestMapping(name = "/api/v1/schedule")
class ScheduleController(
    val scheduleService: ScheduleService
) {
    @GetMapping("/{id}")
    fun fetch(@PathVariable id: UUID)
        : ResponseEntity<*>
        = scheduleService
            .fetch(id)
            .fold(
                { failure(it) },
                { success(it) }
            )
    @PostMapping
    fun create(@RequestBody schedule: Schedule)
        = scheduleService.saveOrUpdate(schedule)

    @PutMapping
    fun update( @RequestBody schedule: Schedule)
        = create(schedule)

    @DeleteMapping
    fun delete( @RequestBody schedule: Schedule)
        = scheduleService.delete(schedule)
}