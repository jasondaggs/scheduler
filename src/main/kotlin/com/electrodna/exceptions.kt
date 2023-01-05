package com.electrodna
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
    "Schedule Not Found",
    HttpStatus.NOT_FOUND,
    "The schedule object [ $id ] was not found."
)






