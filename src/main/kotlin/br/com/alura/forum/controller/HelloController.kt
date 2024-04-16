package br.com.alura.br.com.alura.forum.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Classe de teste
 */
@RestController
@RequestMapping("/hello")
class HelloController {

        @GetMapping
        fun hello(): String {
            return "Hello world, teste!"
        }
}