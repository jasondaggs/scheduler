package com.electrodna.fixtures

import com.electrodna.models.Schedule
import java.util.*
import java.util.random.RandomGenerator

object ScheduleFixture {
    fun nextSchedule() : Schedule {
        return Schedule(UUID.randomUUID(),RandomFixture.nextAlphaNumeric())
    }
}