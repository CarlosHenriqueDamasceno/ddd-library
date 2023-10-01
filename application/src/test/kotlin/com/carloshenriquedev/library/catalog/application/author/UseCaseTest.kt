package com.carloshenriquedev.library.catalog.application.author

import io.mockk.clearMocks
import io.mockk.confirmVerified
import org.junit.jupiter.api.AfterEach

abstract class UseCaseTest {
    @AfterEach
    fun tearDown() {
        val mocks = getMocks()
        confirmVerified(*mocks)
        clearMocks(firstMock = mocks[0], mocks = (mocks.drop(0).toTypedArray()))
    }

    protected abstract fun getMocks(): Array<Any>
}
