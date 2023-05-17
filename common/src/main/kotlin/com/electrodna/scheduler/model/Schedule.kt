package com.electrodna.scheduler.model

import org.springframework.data.annotation.Id
import java.util.*

data class Schedule(
    @Id
    val scheduleId: UUID,
    val name: String
)
