package com.electrodna.scheduler.model

import org.springframework.data.annotation.Id
import java.util.*

data class Task(
    @Id
    val taskId: UUID,
    val taskName: String,
    val command: String
)
