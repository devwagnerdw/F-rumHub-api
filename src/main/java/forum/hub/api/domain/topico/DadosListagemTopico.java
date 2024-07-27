package forum.hub.api.domain.topico;

import forum.hub.api.domain.comentario.DadosComentario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DadosListagemTopico {
    private Long id;
    private String titulo;
    private String mensagem;
    private Date dataCriacao;
    private Status status;
    private Curso curso;
    private int respostas;
    private Long usuarioId;
    private List<DadosComentario> comentarios;


    public static DadosListagemTopico fromTopico(Topico topico) {
        List<DadosComentario> comentariosConvertidos = topico.getComentarios().stream()
                .map(DadosComentario::new)
                .collect(Collectors.toList());

        return new DadosListagemTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getDataCriacao(),
                topico.getStatus(),
                topico.getCurso(),
                topico.getRespostas(),
                topico.getUsuario().getId(),
                comentariosConvertidos);

}}
