package com.electrodna.services

import arrow.core.Some
import arrow.core.Option
import arrow.core.Either
import com.electrodna.models.Schedule
import org.springframework.stereotype.Service
import java.util.*


@Service
class ScheduleService {

    fun fetch(id: UUID) : Either<Throwable,Option<Schedule>> {
        return Either.Right(Some(Schedule(id, "bill")))
    }

    fun create(schedule: Schedule): Result<Boolean> {
        return Result.success(true)
    }

    fun update(schedule: Schedule) {}

    fun delete(schedule: Schedule) {}

}