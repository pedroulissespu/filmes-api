package com.pedroulissespu.filmesapi

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@AutoConfigureMockMvc
class FilmeControllerTest {

    @Autowired lateinit var mockMvc : MockMvc

    @Autowired lateinit var  filmesRepository: FilmesRepository
    @Test
    fun `test find all`(){
        filmesRepository.save(Filme(titulo = "Teste" , atores = "Testes" , genero = "Teste" , classificacao = 1 , preco = "12.99"))

        mockMvc.perform(MockMvcRequestBuilders.get("/filmes"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("\$").isArray)
            .andExpect(MockMvcResultMatchers.jsonPath("\$[0].id").isNumber)
            .andExpect(MockMvcResultMatchers.jsonPath("\$[0].titulo").isString)
            .andExpect(MockMvcResultMatchers.jsonPath("\$[0].atores").isString)
            .andExpect(MockMvcResultMatchers.jsonPath("\$[0].genero").isString)
            .andExpect(MockMvcResultMatchers.jsonPath("\$[0].classificacao").isNumber)
            .andExpect(MockMvcResultMatchers.jsonPath("\$[0].preco").isString)
            .andDo(MockMvcResultHandlers.print())
    }
}