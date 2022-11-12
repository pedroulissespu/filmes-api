package com.pedroulissespu.filmesapi

import org.aspectj.apache.bcel.Repository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/filmes")
class FileController(private val repository: FilmesRepository) {

    @PostMapping
    fun create(@RequestBody filme : Filme) : Filme = repository.save(filme)

    @GetMapping
    fun getAll(): List<Filme> = repository.findAll()

    @GetMapping("/{id}")
    fun getbyId(@PathVariable id: Long) : ResponseEntity<Filme> =
        repository.findById(id).map{
            ResponseEntity.ok(it)
        }.orElse(ResponseEntity.notFound().build())

    @PutMapping("/{id}")
    fun update(@PathVariable id : Long, @RequestBody filme : Filme) : ResponseEntity<Filme> =
        repository.findById(id).map{
            val filmetoUpdate = it.copy(
                titulo = filme.titulo,
                atores = filme.atores,
                genero = filme.genero,
                classificacao = filme.classificacao,
                preco = filme.preco
            )
            ResponseEntity.ok(repository.save(filmetoUpdate))
        }.orElse(ResponseEntity.notFound().build())

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) : ResponseEntity<Void> =
        repository.findById(id).map{
            repository.delete(it)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())
}