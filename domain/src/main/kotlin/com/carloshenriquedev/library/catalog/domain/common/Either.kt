package com.carloshenriquedev.library.catalog.domain.common

sealed class Either<out LEFT, out RIGHT> {
    class Left<LEFT>(val value: LEFT) : Either<LEFT, Nothing>()
    class Right<RIGHT>(val value: RIGHT) : Either<Nothing, RIGHT>()

    fun isLeft() = this is Left
    fun isRight() = this is Right
}