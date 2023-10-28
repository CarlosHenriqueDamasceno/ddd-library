package com.carloshenriquedev.library.catalog.application.common

interface UseCase<IN, OUT> {
    fun execute(command: IN): OUT
}