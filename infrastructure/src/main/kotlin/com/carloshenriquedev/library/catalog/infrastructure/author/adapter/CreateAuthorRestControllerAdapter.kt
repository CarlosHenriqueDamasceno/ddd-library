package com.carloshenriquedev.library.catalog.infrastructure.author.adapter

import com.carloshenriquedev.library.catalog.application.author.port.AuthorOutput
import com.carloshenriquedev.library.catalog.application.author.port.CreateAuthorCommand
import com.carloshenriquedev.library.catalog.application.author.port.CreateAuthorUseCase
import com.carloshenriquedev.library.catalog.application.common.Either
import com.carloshenriquedev.library.catalog.infrastructure.common.adapter.ApiError
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
class CreateAuthorController(val createAuthorUseCase: CreateAuthorUseCase) {

    @PostMapping
    fun create(@Valid @RequestBody data: AuthorRequest): ResponseEntity<Any> {
        val command = data.toCommand()
        return when (val output = createAuthorUseCase.execute(command)) {
            is Either.Left -> {
                val uri = URI.create("/authors/${output.value.id}")
                output.value.let {
                    ResponseEntity.created(uri)
                        .body(AuthorResponse.from(it))
                }
            }

            is Either.Right -> {
                output.value.let {
                    ResponseEntity.unprocessableEntity().run {
                        body(ApiError.from(it))
                    }
                }
            }
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
    @JsonProperty("deleted_at") val deletedAt: String?,
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
