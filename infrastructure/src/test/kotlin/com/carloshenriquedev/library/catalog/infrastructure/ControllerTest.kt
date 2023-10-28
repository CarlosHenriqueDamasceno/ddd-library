package com.carloshenriquedev.library.catalog.infrastructure

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.core.annotation.AliasFor
import org.springframework.test.context.ActiveProfiles
import java.lang.annotation.Inherited
import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@ActiveProfiles("test-integration")
@Inherited
@WebMvcTest
annotation class ControllerTest(
    @get:AliasFor(
        annotation = WebMvcTest::class,
        attribute = "controllers"
    )
    val controllers: Array<KClass<*>> = [],
)
