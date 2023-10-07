package com.carloshenriquedev.library.catalog.infrastructure.common.adapter

import com.carloshenriquedev.library.catalog.domain.common.Error
import com.carloshenriquedev.library.catalog.domain.common.ValidationHandler

data class ApiError(val errors: List<Error>) {
    companion object Builder {
        fun from(handler: ValidationHandler) = ApiError(handler.errors)
    }
}