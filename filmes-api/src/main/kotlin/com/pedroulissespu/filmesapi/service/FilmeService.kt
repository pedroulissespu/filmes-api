package com.pedroulissespu.filmesapi.service

import com.pedroulissespu.filmesapi.model.Filme
import java.util.*

interface FilmeService {
    fun create(filme : Filme) : Filme

    fun getAll(): List<Filme>

    fun getbyId(id: Long) : Optional<Filme>

    fun update(id : Long,filme : Filme) : Optional<Filme>

    fun delete(id: Long)
}