package com.electrodna.scheduler.service

import arrow.core.*
import com.electrodna.scheduler.FakeException
import com.electrodna.scheduler.ScheduleNotFound
import com.electrodna.scheduler.fixtures.ScheduleFixture
import com.electrodna.scheduler.model.Schedule
import com.electrodna.scheduler.repository.ScheduleRepository
import com.ninjasquad.springmockk.MockkBean
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import java.util.*
class ScheduleServiceSpec (
    @MockkBean val scheduleRepository : ScheduleRepository
): FreeSpec() {
    private val scheduleService = ScheduleService(scheduleRepository)
    init {
        "#fetch" - {
            val uuId = UUID.randomUUID()
            val expectedSchedule = Schedule(uuId, "bill")

            fun subject() = scheduleService.fetch(uuId)

            "should return a schedule with an existing UUID " {
                every { scheduleRepository.findById(uuId) } returns Optional.of(expectedSchedule)
                subject() shouldBe Either.Right(expectedSchedule)
            }

            "should return a ScheduleNotFound exception for a non-existing UUID " {
                every { scheduleRepository.findById(uuId) } returns Optional.empty()
                val result = subject()
                result.mapLeft { it::class } shouldBe Either.Left(ScheduleNotFound::class)
                result.mapLeft { it.detail } shouldBe Either.Left(
                    "The schedule object [ $uuId ] was not found."
                )
            }

            "should throw a FakeException when a FakeException is thrown" {
                every { scheduleRepository.findById(uuId) } throws FakeException()
                shouldThrow<FakeException> {
                    subject()
                }
            }
        }

        "#saveOrUpdate" - {
            val schedule = ScheduleFixture.nextSchedule()

            fun subject() = scheduleService.saveOrUpdate(schedule)

            "should save a new schedule" {
                every { scheduleRepository.save(schedule) } returns schedule
                subject() shouldBe schedule;
            }
        }
    }
}