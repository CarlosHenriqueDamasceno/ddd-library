package com.carloshenriquedev.library.catalog.infrastructure

import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.FilterType
import org.springframework.test.context.ActiveProfiles
import java.lang.annotation.Inherited


@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Inherited
@ActiveProfiles("test-integration")
@DataJpaTest
@ComponentScan(
    basePackages = ["com.carloshenriquedev.library.catalog"],
    includeFilters = [ComponentScan.Filter(type = FilterType.REGEX, pattern = [".[JPAGateway]"])]
)
@ExtendWith(DatabaseCleanUpExtension::class)
annotation class DatabaseTest