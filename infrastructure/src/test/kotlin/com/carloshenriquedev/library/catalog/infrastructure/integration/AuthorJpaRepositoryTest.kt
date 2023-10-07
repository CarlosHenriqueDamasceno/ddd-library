package com.carloshenriquedev.library.catalog.infrastructure.integration

import com.carloshenriquedev.library.catalog.application.author.port.AuthorGateway
import com.carloshenriquedev.library.catalog.infrastructure.DatabaseTest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import kotlin.test.assertNotNull

@DatabaseTest
class AuthorJpaRepositoryTest {

    @Autowired
    lateinit var gateway: AuthorGateway

    @Test
    fun `given a author, when call created, should persist author in database`() {
        assertNotNull(gateway)
    }
}