package com.pedroulissespu.filmesapi.controller

import com.pedroulissespu.filmesapi.repository.FilmesRepository
import com.pedroulissespu.filmesapi.model.Filme
import com.pedroulissespu.filmesapi.service.FilmeService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/filmes")
class FileController(private val service: FilmeService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody filme : Filme) : Filme = service.create(filme)

    @GetMapping
    fun getAll(): List<Filme> = service.getAll()

    @GetMapping("/{id}")
    fun getbyId(@PathVariable id: Long) : ResponseEntity<Filme> =
        service.getbyId(id).map{
            ResponseEntity.ok(it)
        }.orElse(ResponseEntity.notFound().build())

    @PutMapping("/{id}")
    fun update(@PathVariable id : Long, @RequestBody filme : Filme) : ResponseEntity<Filme> =
        service.update(id,filme).map{
            ResponseEntity.ok(it)
        }.orElse(ResponseEntity.notFound().build())

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) : ResponseEntity<Void> {
        service.delete(id)
        return ResponseEntity<Void>(HttpStatus.OK)
    }
}