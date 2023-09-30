package com.carloshenriquedev.library.catalog.application.author

import com.carloshenriquedev.library.catalog.application.author.useCase.CreateAuthorCommand
import com.carloshenriquedev.library.catalog.application.author.useCase.DefaultCreateAuthorUseCase
import com.carloshenriquedev.library.catalog.domain.author.Author
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class DefaultCreateAuthorUseCaseTest {


    private val bookRepository = mockk<AuthorRepository>()

    @InjectMockKs
    private val useCase = DefaultCreateAuthorUseCase(bookRepository)

    @Test
    fun `given a valid command, should create an author`() {
        val authorSlot = slot<Author>()
        val expectedName = "Uncle Bob"
        val command = CreateAuthorCommand(expectedName)

        every { bookRepository.create(capture(authorSlot)) } answers { firstArg<Author>() }

        println(useCase)

        val result = useCase.execute(command)

        assertEquals(expectedName, result.name)
        assertEquals(result.id, authorSlot.captured.id.value)
        assertEquals(result.name, authorSlot.captured.name)
        assertEquals(result.createdAt, authorSlot.captured.createdAt)
        assertEquals(result.updatedAt, authorSlot.captured.updatedAt)
        assertEquals(result.deletedAt, authorSlot.captured.deletedAt)

        verify(exactly = 1) { bookRepository.create(authorSlot.captured) }
    }
}