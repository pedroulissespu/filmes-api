package com.pedroulissespu.filmesapi.repository

import com.pedroulissespu.filmesapi.model.Filme
import org.springframework.data.jpa.repository.JpaRepository

interface FilmesRepository : JpaRepository<Filme, Long> {
}