package com.carloshenriquedev.library.catalog.infrastructure

import com.carloshenriquedev.library.catalog.infrastructure.author.adapter.AuthorJpaRepository
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.CrudRepository
import org.springframework.test.context.junit.jupiter.SpringExtension

class DatabaseCleanUpExtension() : BeforeEachCallback {

    override fun beforeEach(context: ExtensionContext) {

        val appContext = SpringExtension.getApplicationContext(context)

        cleanUp(
            listOf(
                appContext.getBean(AuthorJpaRepository::class.java)
            )
        )

        appContext.getBean(TestEntityManager::class.java).run {
            this.flush()
            this.clear()
        }
    }

    private fun cleanUp(repositories: Collection<CrudRepository<*, *>>) {
        repositories.forEach {
            it.deleteAll()
        }
    }
}
