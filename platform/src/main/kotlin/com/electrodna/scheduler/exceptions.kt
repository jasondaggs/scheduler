package com.electrodna.scheduler
import org.springframework.http.HttpStatus

open class ApiException(
    val type: String,
    val title: String,
    val status: HttpStatus,
    val detail: String
) : Exception()








