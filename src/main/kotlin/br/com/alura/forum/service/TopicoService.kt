package br.com.alura.br.com.alura.forum.service

import br.com.alura.br.com.alura.forum.dto.AtualizacaoTopicoForm
import br.com.alura.br.com.alura.forum.dto.NovoTopicoForm
import br.com.alura.br.com.alura.forum.dto.TopicoView
import br.com.alura.br.com.alura.forum.exception.NotFoundException
import br.com.alura.br.com.alura.forum.mapper.TopicoFormMapper
import br.com.alura.br.com.alura.forum.mapper.TopicoViewMapper
import br.com.alura.br.com.alura.forum.model.Topico
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class TopicoService(
    private var topicos: List<Topico> = ArrayList(),
    private val topicoViewMapper: TopicoViewMapper,
    private val topicoFormMapper: TopicoFormMapper,
    private val notFoundMessage: String = "Topico não encontrado!"
) {

    // val é read-only, ou seja, não pode ser alterado igual um final em JAVA
    // var pode ser alterado

    fun listar(): List<TopicoView> {
        //mapeamos a lista de topicos para uma lista de TopicoView
        return topicos.stream().map { t ->
            topicoViewMapper.map(t)
        }.collect(Collectors.toList())
    }

    fun buscarPorId(id: Long): TopicoView {
        //filtramos o tópico da lista de topicos cadastrada anteriormente
        val topico = topicos.stream().filter { t ->
            t.id == id
        }.findFirst().orElseThrow { NotFoundException(notFoundMessage) }

        return topicoViewMapper.map(topico)
    }

    fun cadastrar(form: NovoTopicoForm): TopicoView {

        //criamos um novo tópico com os dados vindos do Form
        val topico = topicoFormMapper.map(form) //mapeamos o form para Topico
        topico.id = topicos.size.toLong() + 1 //geramos um id para o tópico automaticamente
        topicos = topicos.plus(topico) //adicionamos o tópico na lista de topicos
        return topicoViewMapper.map(topico) //retornamos o tópico mapeado para TopicoView
    }

    fun atualizar(form: AtualizacaoTopicoForm): TopicoView {
        //filtramos o tópico da lista de topicos cadastrada anteriormente
        val topico = topicos.stream().filter { t ->
            t.id == form.id
        }.findFirst().orElseThrow { NotFoundException(notFoundMessage) }

        //removemos o tópico pesquisado da lista de topicos e incluimos ele novamente com os dados atualizados vindos do Form
        val topicoAtualizado = Topico(
            id = form.id,
            titulo = form.titulo,
            mensagem = form.mensagem,
            autor = topico.autor,
            curso = topico.curso,
            respostas = topico.respostas,
            status = topico.status,
            dataCriacao = topico.dataCriacao
        )
        topicos =
            topicos.minus(topico).plus(topicoAtualizado) //removemos o tópico antigo e adicionamos o tópico atualizado

        return topicoViewMapper.map(topicoAtualizado) //retornamos o tópico mapeado para TopicoView

    }

    fun deletar(id: Long) {
        //filtramos o tópico da lista de topicos cadastrada anteriormente

        val topico = topicos.stream().filter {
            t -> t.id == id
        }.findFirst().orElseThrow { NotFoundException(notFoundMessage) }
        //}.findFirst().get()

        //removemos o tópico da lista de topicos
        topicos = topicos.minus(topico)

    }


}