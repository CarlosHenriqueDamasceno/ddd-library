package com.carloshenriquedev.library.catalog.infrastructure.author

import com.carloshenriquedev.library.catalog.application.author.useCase.AuthorOutput
import com.carloshenriquedev.library.catalog.application.author.useCase.CreateAuthorCommand
import com.carloshenriquedev.library.catalog.application.author.useCase.CreateAuthorUseCase
import com.carloshenriquedev.library.catalog.application.common.Either
import com.carloshenriquedev.library.catalog.domain.common.Error
import com.carloshenriquedev.library.catalog.domain.common.ValidationHandler
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/api/v1/authors")
class AuthorController(val createAuthorUseCase: CreateAuthorUseCase) {

    @PostMapping
    fun create(@Valid @RequestBody data: AuthorRequest): ResponseEntity<Any> {
        val command = data.toCommand()
        val output = createAuthorUseCase.execute(command)
        return when (output) {
            is Either.Left -> {
                val uri = URI.create("/authors/${output.value.id}")
                val response = AuthorResponse.from(output.value)
                ResponseEntity.created(uri)
                    .body(response)
            }

            is Either.Right -> ResponseEntity.unprocessableEntity().body(ApiError.from(output.value))
        }
    }
}

data class AuthorRequest(@NotBlank val name: String) {
    fun toCommand() = CreateAuthorCommand(name)
}

data class AuthorResponse(
    @JsonProperty("id") val id: String,
    @JsonProperty("name") val name: String,
    @JsonProperty("created_at") val createdAt: String,
    @JsonProperty("deleted_at") val deletedAt: String?
) {

    companion object Builder {
        fun from(output: AuthorOutput): AuthorResponse {
            return AuthorResponse(
                output.id,
                output.name,
                output.createdAt.toString(),
                output.deletedAt?.toString()
            )
        }
    }

}

data class ApiError(val errors: List<Error>) {
    companion object Builder {
        fun from(handler: ValidationHandler) = ApiError(handler.errors)
    }
}
