package com.electrodna.scheduler

import org.springframework.http.HttpStatus
import java.util.*

data class ScheduleNotFound(val id: UUID) : ApiException(
    "/errors/schedule-not-found",
    "Schedule Not Found",
    HttpStatus.NOT_FOUND,
    "The schedule object [ $id ] was not found."
)