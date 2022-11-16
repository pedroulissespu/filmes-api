package com.pedroulissespu.filmesapi.service

import com.pedroulissespu.filmesapi.model.Filme
import com.pedroulissespu.filmesapi.repository.FilmesRepository
import org.springframework.stereotype.Service
import org.springframework.util.Assert
import java.lang.RuntimeException
import java.util.*

@Service
class FilmeServiceImpl(private val repository: FilmesRepository) : FilmeService {
    override fun create(filme: Filme): Filme {
        //Possiveis erros com o titulo
        Assert.hasLength(filme.titulo, "[titulo] não pode estar em branco !")
        Assert.isTrue(filme.titulo.length >= 5 , "[titulo] deve ter no minimo 5 caracteres!")
        //Possiveis erros com atores
        Assert.hasLength(filme.atores, "[atores] não pode estar em branco !")
        Assert.isTrue(filme.titulo.length >= 4 , "[atores] deve ter no minimo 4 caracteres!")
        //Possiveis erros com genero
        Assert.hasLength(filme.genero, "[genero] não pode estar em branco !")
        Assert.isTrue(filme.genero.length >= 5 , "[genero] deve ter no minimo 5 caracteres")
        //Possiveis erros com classificacao
        Assert.hasLength(filme.classificacao.toString(), "[classificacao] não pode estar em branco !")
        Assert.isTrue(filme.classificacao.toString().isNotEmpty() , "[classificacao] deve ter pelo menos uma classificação")
        //Possiveis erros com preco
        Assert.hasLength(filme.preco, "[preco] não pode estar em branco !")
        Assert.isTrue(filme.preco.length >= 5 , "[preco] deve ter no minimo 5 caracteres")

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
        if(!optional.isEmpty){
            return optional
        }

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
        }.orElseThrow{
            throw RuntimeException("Id not found $id")
        }
    }
}