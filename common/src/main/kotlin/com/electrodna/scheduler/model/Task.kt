package com.electrodna.scheduler.model

import java.net.URI
import java.util.*

data class Task(
    val taskId: UUID,
    val taskName: String,
    val command: String
)
