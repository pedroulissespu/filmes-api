package com.pedroulissespu.filmesapi.service

import com.pedroulissespu.filmesapi.model.Filme
import com.pedroulissespu.filmesapi.repository.FilmesRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import java.util.*

@Service
class FilmeServiceImpl(private val repository: FilmesRepository) : FilmeService {
    override fun create(filme: Filme): Filme {
        return repository.save(filme)
    }

    override fun getAll(): List<Filme> {
        return repository.findAll()
    }

    override fun getbyId(id: Long): Optional<Filme> {
        return repository.findById(id)
    }

    override fun update(id: Long, filme: Filme): Optional<Filme> {
        val optional = getbyId(id)
        if(optional.isEmpty)Optional.empty<Filme>()

        return optional.map {
            val filmetoUpdate = it.copy(
                titulo = filme.titulo,
                atores = filme.atores,
                genero = filme.genero,
                classificacao = filme.classificacao,
                preco = filme.preco
            )
            repository.save(filmetoUpdate)
        }
    }

    override fun delete(id: Long) {
        repository.findById(id).map{
            repository.delete(it)
        }.orElseThrow{throw RuntimeException("Id not found $id")}
    }
}