package com.electrodna.services

import arrow.core.*
import com.electrodna.FakeException
import com.electrodna.ScheduleNotFound
import com.electrodna.fixtures.ScheduleFixture
import com.electrodna.models.Schedule
import com.electrodna.repositories.ScheduleRepository
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
                val exception = FakeException()
                every { scheduleRepository.findById(uuId) } throws exception
                shouldThrow<FakeException> {
                    subject()
                }
            }
        }
        "#saveOrUpdate" - {
            val schedule = ScheduleFixture.nextSchedule()
            fun subject() = scheduleService.saveOrUpdate(schedule)
            "should save a new Schedule" {
                every { scheduleRepository.save(schedule) } returns schedule
                subject() shouldBe Either.Right(schedule);
            }
        }
    }
}