package br.com.alura.mapper

import br.com.alura.br.com.alura.mapper.Mapper
import br.com.alura.dto.NovoTopicoForm
import br.com.alura.model.Topico
import br.com.alura.service.CursoService
import br.com.alura.service.UsuarioService
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
