package com.carloshenriquedev.library.catalog.infrastructure

import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@AutoConfiguration
class LibraryApplication

fun main(args: Array<String>) {
    runApplication<LibraryApplication>(*args)
}