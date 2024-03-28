package br.com.alura.service

import br.com.alura.dto.NovoTopicoForm
import br.com.alura.dto.TopicoView
import br.com.alura.mapper.TopicoFormMapper
import br.com.alura.mapper.TopicoViewMapper
import br.com.alura.model.Topico
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class TopicoService(
    private var topicos: List<Topico> = ArrayList(),
    private val topicoViewMapper: TopicoViewMapper,
    private val topicoFormMapper: TopicoFormMapper
) {

    fun listar(): List<TopicoView> {
        return topicos.stream().map {
            t -> topicoViewMapper.map(t)
        }.collect(Collectors.toList())
    }

    fun buscarPorId(id: Long): TopicoView {
        val topico = topicos.stream().filter { t ->
            t.id == id
        }.findFirst().get()

        return topicoViewMapper.map(topico)
    }

    fun cadastrar(form: NovoTopicoForm) {

        val topico = topicoFormMapper.map(form)
        topico.id = topicos.size.toLong() + 1
        topicos = topicos.plus(topico)

    }

    // val é read-only, ou seja, não pode ser alterado igual um final em JAVA
    // var pode ser alterado

}