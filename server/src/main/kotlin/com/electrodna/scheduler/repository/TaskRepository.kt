package com.electrodna.scheduler.repository

import com.electrodna.scheduler.model.Task
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface TaskRepository : CrudRepository<Task,UUID> {}