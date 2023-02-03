package com.electrodna.scheduler
import org.springframework.http.HttpStatus
import java.util.*

sealed class ApiException(
    val type: String,
    val title: String,
    val status: HttpStatus,
    val detail: String
) : Exception()

class ScheduleNotFound(id: UUID) : ApiException(
    "/errors/schedule-not-found",
    "No Results Found",
    HttpStatus.NOT_FOUND,
    "The query for schedule object [ $id ] returned no results."
)
