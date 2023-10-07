package com.carloshenriquedev.library.catalog.infrastructure.integration

import com.carloshenriquedev.library.catalog.application.author.port.AuthorOutput
import com.carloshenriquedev.library.catalog.application.author.port.CreateAuthorCommand
import com.carloshenriquedev.library.catalog.application.author.port.CreateAuthorUseCase
import com.carloshenriquedev.library.catalog.application.common.Either
import com.carloshenriquedev.library.catalog.domain.common.Error
import com.carloshenriquedev.library.catalog.domain.common.ValidationHandler
import com.carloshenriquedev.library.catalog.infrastructure.author.adapter.AuthorRequest
import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.slot
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.Matchers.hasSize
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import java.time.Instant
import java.time.temporal.ChronoUnit

@WebMvcTest
class AuthorControllerTest {

    @MockkBean
    lateinit var createUserUseCase: CreateAuthorUseCase

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var mapper: ObjectMapper

    @Test
    fun `should return 201 created when call create endpoint`() {
        val expectedId = "123"
        val expectedName = "Vaughn Vernon"
        val expectedCreatedAt = Instant.now().truncatedTo(ChronoUnit.MICROS)
        val expectedDeletedAt: Instant? = null

        val authorRequest = AuthorRequest(expectedName)

        val commandSlot = slot<CreateAuthorCommand>()

        every { createUserUseCase.execute(capture(commandSlot)) } answers {
            Either.Left(
                AuthorOutput(
                    expectedId,
                    expectedName,
                    expectedCreatedAt,
                    expectedDeletedAt
                )
            )
        }

        mockMvc.post(
            urlTemplate = "/api/v1/authors"
        ) {
            contentType = MediaType.APPLICATION_JSON
            content = mapper.writeValueAsString(authorRequest)
        }.andExpect {
            status { isCreated() }
            header { string("Location", "/authors/123") }
            jsonPath("$.id", equalTo(expectedId))
            jsonPath("$.name", equalTo(expectedName))
            jsonPath("$.created_at", equalTo(expectedCreatedAt.toString()))
            jsonPath("$.deleted_at", equalTo(expectedDeletedAt?.toString()))
        }
    }

    @Test
    fun `should return a treated error when try to create an user with empty name`() {
        val expectedName = ""
        val expectedErrorCount = 1
        val expectedErrorMessage = "Author's name can not be empty."

        val authorRequest = AuthorRequest(expectedName)

        every { createUserUseCase.execute(any()) } answers {
            Either.Right(ValidationHandler().apply {
                this.handle(
                    Error("Author's name can not be empty.")
                )
            })
        }

        mockMvc.post(
            urlTemplate = "/api/v1/authors"
        ) {
            contentType = MediaType.APPLICATION_JSON
            content = mapper.writeValueAsString(authorRequest)
        }.andExpect {
            status { isUnprocessableEntity() }
            jsonPath("$.errors", hasSize<Int>(expectedErrorCount))
            jsonPath("$.errors[0].message", equalTo(expectedErrorMessage))
        }
    }

}