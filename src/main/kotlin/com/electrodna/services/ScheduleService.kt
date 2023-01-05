package com.electrodna.services

import arrow.core.*
import com.electrodna.ApiException
import com.electrodna.ScheduleNotFound
import com.electrodna.models.Schedule
import com.electrodna.repositories.ScheduleRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ScheduleService ( val scheduleRepository : ScheduleRepository ){
    fun fetch(id: UUID) : Either<ApiException,Schedule> =
        scheduleRepository
            .findById(id)
            .let {
                return if (it.isPresent)
                    Either.Right(it.get())
                else
                    Either.Left(ScheduleNotFound(id))
            }
    fun saveOrUpdate(schedule: Schedule) : Either<ApiException,Schedule> =
        Either.Right(scheduleRepository.save(schedule))

    fun delete(schedule: Schedule) = scheduleRepository.delete(schedule);

}