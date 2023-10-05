package com.carloshenriquedev.library.catalog.application.common

sealed class Either<out LEFT, out RIGHT> {
    data class Left<LEFT>(val value: LEFT) : Either<LEFT, Nothing>()
    data class Right<RIGHT>(val value: RIGHT) : Either<Nothing, RIGHT>()

    fun isLeft() = this is Left
    fun isRight() = this is Right
}

