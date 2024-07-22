package forum.hub.api.domain.topico;

import forum.hub.api.domain.comentario.Comentario;
import forum.hub.api.domain.comentario.DadosComentario;

import java.util.Date;
import java.util.List;

import java.util.List;

public record DadosListagemTopico(
        Long id,
        String titulo,
        String mensagem,
        Date dataCriacao,
        Status status,
        Curso curso,
        int respostas,
        Long usuarioId,
        List<DadosComentario> comentarios
) {
    public DadosListagemTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getDataCriacao(),
                topico.getStatus(),
                topico.getCurso(),
                topico.getRespostas(),
                topico.getUsuario().getId(),
                topico.getComentarios().stream()
                        .map(DadosComentario::new)
                        .toList()
        );
    }
}