package br.com.alura.br.com.alura.forum.mapper

interface Mapper<T, U> {

    fun map(t: T): U
}
