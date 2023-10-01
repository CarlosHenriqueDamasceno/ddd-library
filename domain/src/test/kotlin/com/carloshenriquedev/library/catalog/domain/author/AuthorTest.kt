package com.carloshenriquedev.library.catalog.domain.author

import com.carloshenriquedev.library.catalog.domain.common.DomainException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.fail
import kotlin.test.assertEquals
import kotlin.test.assertIs

class AuthorTest {

    @Test
    fun `given valid parameters, should instantiate a new author`() {
        // given
        val expectedName = "Uncle Bob"

        // when / Then
        try {
            Author.buildNewAuthor(expectedName)
        } catch (exception: Throwable) {
            fail(exception.message)
        }
    }

    @Test
    fun `given a empty name, should throw domain exception`() {
        // given
        val expectedName = ""
        val expectedErrorCount = 1
        val expectedErrorMessage = "Author's name can not be empty."

        // when / Then

        val exception = assertThrows<DomainException> {
            Author.buildNewAuthor(expectedName)
        }

        assertIs<DomainException>(exception)
        assertEquals(expectedErrorCount, exception.handler.size)
        assertEquals(expectedErrorMessage, exception.handler[0].message)

    }
}