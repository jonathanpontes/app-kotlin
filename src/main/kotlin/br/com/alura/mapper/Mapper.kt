package br.com.alura.br.com.alura.mapper

interface Mapper<T, U> {

    fun map(t: T): U
}
