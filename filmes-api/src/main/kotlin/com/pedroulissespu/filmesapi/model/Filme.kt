package com.pedroulissespu.filmesapi.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id


@Entity(name = "filmes")
data class Filme {
    @Id @GeneratedValue
    var id: Long? = null,
    val titulo : String,
    val atores : String,
    val genero : String,
    val classificacao : Long,
    val preco : String
}