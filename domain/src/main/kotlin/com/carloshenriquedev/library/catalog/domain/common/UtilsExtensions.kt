package com.carloshenriquedev.library.catalog.domain.common

import java.time.Instant
import java.time.temporal.ChronoUnit

internal fun nowInMiliSeconds() = Instant.now().truncatedTo(ChronoUnit.MICROS)
