package com.electrodna.controllers

import com.electrodna.models.Schedule
import com.electrodna.services.ScheduleService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import java.util.*


@Controller
@RequestMapping(name = "api/v1/schedule")
class ScheduleController( val scheduleService: ScheduleService) {

    @GetMapping("/{id}")
    fun fetch(@PathVariable id: UUID) : ResponseEntity<Any> {
        return scheduleService.fetch(id).fold(
            { ResponseEntity.notFound().build() },
            { ResponseEntity.ok(it) }
        )
    }

    @PostMapping
    fun create(schedule: Schedule) : ResponseEntity<Any> {
        return scheduleService.create(schedule).fold({ResponseEntity.ok().build()},
            {ResponseEntity.badRequest().build()})
    }

    @PutMapping
    fun update( schedule: Schedule) {

    }

    @DeleteMapping
    fun delete( schedule: Schedule) {

    }

}