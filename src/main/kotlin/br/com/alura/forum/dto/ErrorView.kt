package br.com.alura.br.com.alura.forum.dto

import java.time.LocalDateTime

data class ErrorView(
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val status: Int,
    val error: String,
    val message: String?, // o atributo message pode ser nulo por conta do ? no final
    val path: String

)
