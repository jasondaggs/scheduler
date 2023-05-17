package com.electrodna.scheduler.service

import arrow.core.*
import com.electrodna.scheduler.ApiException
import com.electrodna.scheduler.ScheduleNotFound
import com.electrodna.scheduler.model.Schedule
import com.electrodna.scheduler.model.Task
import com.electrodna.scheduler.repository.ScheduleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.UUID

@Service("scheduleService")
class ScheduleService () {

    @Autowired
    private lateinit var scheduleRepository: ScheduleRepository

    fun fetch(id: UUID) : Either<ApiException, Schedule> =
        scheduleRepository
            .findById(id).toOption()
            .filter { it.isPresent }
            .map { it.get() }
            .toEither { ScheduleNotFound(id) }

    fun saveOrUpdate(schedule: Schedule) : Schedule =
        scheduleRepository.save(schedule)

    fun delete(schedule: Schedule) =
        scheduleRepository.delete(schedule);

    fun fetchTasks(schedule: Schedule) = listOf<Task>();

}