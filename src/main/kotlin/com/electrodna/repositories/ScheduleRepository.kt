package com.electrodna.repositories

import com.electrodna.models.Schedule
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ScheduleRepository : CrudRepository<Schedule, UUID> {
}