package com.electrodna.scheduler.fixtures

import com.electrodna.scheduler.model.Schedule
import java.util.*

object ScheduleFixture {
    fun nextSchedule() : Schedule {
        return Schedule(UUID.randomUUID(), RandomFixture.nextAlphaNumeric())
    }
}