package com.pedroulissespu.filmesapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FilmesApiApplication

fun main(args: Array<String>) {
	runApplication<FilmesApiApplication>(*args)
}
