package com.carloshenriquedev.library.catalog.infrastructure.author.adapter

import com.carloshenriquedev.library.catalog.domain.author.Author
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.Instant

@Entity
@Table(name = "authors")
class AuthorJPAEntity(
    @Id
    var id: String,

    @Column(name = "name", nullable = false)
    var name: String,

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME(6)")
    var createdAt: Instant,

    @Column(name = "updated_at", nullable = false, columnDefinition = "DATETIME(6)")
    var updatedAt: Instant,

    @Column(name = "deleted_at", nullable = true, columnDefinition = "DATETIME(6)")
    var deletedAt: Instant?,
) {

    companion object Builder {
        fun from(author: Author) = AuthorJPAEntity(
            author.id.value,
            author.name,
            author.createdAt,
            author.updatedAt,
            author.deletedAt
        )
    }

    fun toAggragate() = Author.restore(
        id, name, createdAt, updatedAt, deletedAt
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AuthorJPAEntity

        if (id != other.id) return false
        if (name != other.name) return false
        if (createdAt != other.createdAt) return false
        if (updatedAt != other.updatedAt) return false
        if (deletedAt != other.deletedAt) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + createdAt.hashCode()
        result = 31 * result + updatedAt.hashCode()
        result = 31 * result + deletedAt.hashCode()
        return result
    }
}