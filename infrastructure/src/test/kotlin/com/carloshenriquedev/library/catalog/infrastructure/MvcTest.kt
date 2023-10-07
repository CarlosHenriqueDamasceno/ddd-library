package com.carloshenriquedev.library.catalog.infrastructure

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc

@WebMvcTest
class MvcTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    /* @Test
     fun `should get 404 when access server root URL`() {
         mockMvc.get("").andExpect {
             status { isNotFound() }
         }
     }*/
}