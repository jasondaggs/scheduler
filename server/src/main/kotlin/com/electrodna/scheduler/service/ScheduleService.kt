package com.electrodna.scheduler.service

import arrow.core.*
import com.electrodna.scheduler.ApiException
import com.electrodna.scheduler.ScheduleNotFound
import com.electrodna.scheduler.model.Schedule
import com.electrodna.scheduler.repository.ScheduleRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ScheduleService ( val scheduleRepository : ScheduleRepository){
    fun fetch(id: UUID) : Either<ApiException, Schedule> =
        scheduleRepository
            .findById(id)
            .let {
                return if (it.isPresent)
                    Either.Right(it.get())
                else
                    Either.Left(ScheduleNotFound(id))
            }
    fun saveOrUpdate(schedule: Schedule) : Schedule =
        scheduleRepository.save(schedule)

    fun delete(schedule: Schedule) =
        scheduleRepository.delete(schedule);

    fun fetchTasks(schedule: Schedule) =
        scheduleRepository.loadTaskForSchedule(schedule.scheduleId)

}