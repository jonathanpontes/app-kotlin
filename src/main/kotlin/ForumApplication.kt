package br.com.alura

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class ForumApplication

fun main(args: Array<String>) {
    runApplication<ForumApplication>(*args)
}
