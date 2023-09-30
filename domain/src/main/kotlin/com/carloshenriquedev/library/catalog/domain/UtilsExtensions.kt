package com.carloshenriquedev.library.catalog.domain

import java.time.Instant
import java.time.temporal.ChronoUnit

fun nowInMiliSeconds(): Instant {
    return Instant.now().truncatedTo(ChronoUnit.MICROS)
}