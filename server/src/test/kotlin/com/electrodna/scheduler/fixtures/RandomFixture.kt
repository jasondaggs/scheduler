package com.electrodna.scheduler.fixtures

import kotlin.random.Random

object RandomFixture {
    private val charPool : List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

    fun nextAlphaNumeric(length: Int = 10) = (1..length)
    .map { Random.nextInt(0, charPool.size).let { charPool[it] } }
    .joinToString("")
}