package com.carloshenriquedev.library.catalog.domain.common

abstract class ValueObject

abstract class Identifier(val value: String) : ValueObject() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Identifier

        return value == other.value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }
}

abstract class Entity<ID : Identifier>(val id: ID) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Entity<*>

        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}

abstract class AggregateRoot<ID : Identifier>(id: ID) : Entity<ID>(id) {
    protected abstract fun validate()
}
