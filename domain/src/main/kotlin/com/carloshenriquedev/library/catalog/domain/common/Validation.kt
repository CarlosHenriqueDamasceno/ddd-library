package com.carloshenriquedev.library.catalog.domain.common

data class Error(val message: String)

open class ValidationHandler {

    private val _errors: MutableList<Error> = mutableListOf()
    val errors: List<Error>
        get() = _errors.toList()
    val size: Int
        get() = errors.size

    operator fun get(index: Int) = errors[index]

    open fun handle(error: Error) {
        _errors.add(error)
    }
}

internal abstract class Validator(val handler: ValidationHandler) {
    abstract fun validate()
}

data class DomainException(
    override val message: String,
    val handler: ValidationHandler
) : RuntimeException(message, null, true, false)
