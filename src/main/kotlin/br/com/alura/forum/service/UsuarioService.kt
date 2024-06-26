package br.com.alura.br.com.alura.forum.service

import br.com.alura.br.com.alura.forum.model.Usuario
import org.springframework.stereotype.Service

@Service
class UsuarioService(var usuarios: List<Usuario>) {

    init {

        val usuario = Usuario(
            id = 1,
            nome = "Jonathan",
            email = "teste.teste@teste.com"
        )
        usuarios = listOf(usuario)
    }
    fun buscarPorId(idAutor: Long): Usuario {
        return usuarios.stream().filter {
            u -> u.id == idAutor
        }.findFirst().get()


    }
}