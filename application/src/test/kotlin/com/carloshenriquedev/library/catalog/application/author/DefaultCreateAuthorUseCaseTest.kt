package com.carloshenriquedev.library.catalog.application.author

import com.carloshenriquedev.library.catalog.application.author.useCase.CreateAuthorCommand
import com.carloshenriquedev.library.catalog.application.author.useCase.DefaultCreateAuthorUseCase
import com.carloshenriquedev.library.catalog.domain.author.Author
import com.carloshenriquedev.library.catalog.domain.common.Either
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.fail

class DefaultCreateAuthorUseCaseTest : UseCaseTest() {

    private val authorRepository = mockk<AuthorRepository>()

    private val useCase = DefaultCreateAuthorUseCase(authorRepository)

    @Test
    fun `should create an author when given a valid command`() {
        //given
        val authorSlot = slot<Author>()
        val expectedName = "Uncle Bob"
        val command = CreateAuthorCommand(expectedName)

        every { authorRepository.create(capture(authorSlot)) } answers { firstArg<Author>() }

        //when
        val result = useCase.execute(command)

        // then
        if (result is Either.Left) {
            assertEquals(expectedName, result.value.name)
            assertEquals(result.value.id, authorSlot.captured.id.value)
            assertEquals(result.value.name, authorSlot.captured.name)
            assertEquals(result.value.createdAt, authorSlot.captured.createdAt)
            assertEquals(result.value.updatedAt, authorSlot.captured.updatedAt)
            assertEquals(result.value.deletedAt, authorSlot.captured.deletedAt)
            verify(exactly = 1) { authorRepository.create(authorSlot.captured) }
        } else {
            fail("Result must be Right")
        }

    }

    @Test
    fun `should return and error when trying to create an author with empty name`() {
        //given
        val expectedName = " "
        val expectedErrorCount = 1
        val expectedErrorMessage = "Author's name can not be empty."
        val command = CreateAuthorCommand(expectedName)
        //when
        val result = useCase.execute(command)
        // then
        if (result is Either.Right) {
            assertEquals(expectedErrorCount, result.value.size)
            assertEquals(expectedErrorMessage, result.value[0].message)
        }

    }

    override fun getMocks(): Array<Any> {
        return arrayOf(authorRepository)
    }
}