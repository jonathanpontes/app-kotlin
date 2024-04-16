package br.com.alura.br.com.alura.forum.mapper

import br.com.alura.br.com.alura.forum.dto.NovoTopicoForm
import br.com.alura.br.com.alura.forum.model.Topico
import br.com.alura.br.com.alura.forum.service.CursoService
import br.com.alura.br.com.alura.forum.service.UsuarioService
import org.springframework.stereotype.Component

@Component
class TopicoFormMapper(
    private val usuarioService: UsuarioService,
    private val cursoService: CursoService
) : Mapper<NovoTopicoForm, Topico> {

    override fun map(t: NovoTopicoForm): Topico {
        return Topico(
            titulo = t.titulo,
            mensagem = t.mensagem,
            curso = cursoService.buscarPorId(t.idCurso),
            autor = usuarioService.buscarPorId(t.idAutor)
        )


    }
}
