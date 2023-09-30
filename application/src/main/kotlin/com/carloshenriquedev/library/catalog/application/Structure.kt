package com.carloshenriquedev.library.catalog.application

interface UseCase<IN, OUT> {
    fun execute(command: IN): OUT
}