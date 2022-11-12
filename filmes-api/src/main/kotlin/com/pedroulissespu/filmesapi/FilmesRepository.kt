package com.pedroulissespu.filmesapi

import org.springframework.data.jpa.repository.JpaRepository

interface FilmesRepository : JpaRepository<Filme, Long> {
}