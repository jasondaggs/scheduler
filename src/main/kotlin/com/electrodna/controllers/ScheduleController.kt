package com.electrodna.controllers

import com.electrodna.models.Schedule
import com.electrodna.services.ScheduleService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import java.util.*
import arrow.core.Either
import com.electrodna.ApiException

typealias ApiResponse<T> = ResponseEntity<Either<ApiException,T>>

fun <T>success(entity: T) : ApiResponse<T> {
    return ResponseEntity.ok(Either.Right(entity))
}

fun <T>failure(schedulerException: ApiException ) : ApiResponse<T> {
    return ResponseEntity(Either.Left(schedulerException),schedulerException.status)
}


@Controller
@RequestMapping(name = "api/v1/schedule")
class ScheduleController( val scheduleService: ScheduleService) {

    @GetMapping("/{id}")
    fun fetch(@PathVariable id: UUID) : ApiResponse<Schedule> {
        return scheduleService
            .fetch(id)
            .fold(
                { failure(it) },
                { success(it) }
            )
    }

    @PostMapping
    fun create(@RequestBody schedule: Schedule) : ApiResponse<Schedule> {
        return scheduleService
            .saveOrUpdate(schedule)
            .fold(
                { failure(it) },
                { success(it) }
            )
    }

    @PutMapping
    fun update( schedule: Schedule) : ApiResponse<Schedule> = create(schedule)

    @DeleteMapping
    fun delete( schedule: Schedule) = scheduleService.delete(schedule)


}