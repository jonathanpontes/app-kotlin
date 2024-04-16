package br.com.alura.br.com.alura.forum.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class NovoTopicoForm (

    @field:NotEmpty(message = "O título não pode ser vazio.")
    @field:Size(min = 5, max = 100, message = "O título deve ter entre 5 e 100 caracteres.")
    val titulo: String,

    @field:NotEmpty(message = "A mensagem não pode ser vazia.")
    val mensagem: String,

    @field:NotNull(message = "O id do curso não pode ser nulo.")
    val idCurso: Long,

    @field:NotNull(message = "O id do autor não pode ser nulo.")
    val idAutor: Long
)