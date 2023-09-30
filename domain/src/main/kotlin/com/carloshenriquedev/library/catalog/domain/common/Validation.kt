package com.carloshenriquedev.library.catalog.domain.common

data class Error(val message: String)

open class ValidationHandler {

    private val errors: MutableList<Error> = mutableListOf()
    val size: Int
        get() = errors.size

    operator fun get(index: Int) = errors[index]

    open fun handle(error: Error) {
        errors.add(error)
    }
}

abstract class Validator(val handler: ValidationHandler) {
    abstract fun validate()
}
