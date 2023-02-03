package com.electrodna.scheduler.repository

import com.electrodna.scheduler.db.query
import com.electrodna.scheduler.db.Column
import com.electrodna.scheduler.model.Schedule
import com.electrodna.scheduler.model.Task
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Repository
interface ScheduleRepository : CrudRepository<Schedule, UUID> {
    @Transactional
    fun loadTaskForSchedule(id: UUID) : List<Task>
    {
        return """
            select 
                *
            from 
                tasks t
            where 
                t.id = @id
        """.trimIndent()
            .query<Task>(Column("id",id))
            .execute()
    }
}