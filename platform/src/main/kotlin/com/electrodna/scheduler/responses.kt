package com.electrodna.scheduler

import org.springframework.http.ResponseEntity

fun <T>success(entity: T)
        : ResponseEntity<T> {
    return ResponseEntity.ok(entity)
}

fun failure(apiException: ApiException)
        : ResponseEntity<ApiException> {
    return ResponseEntity(
        apiException,
        apiException.status
    )
}