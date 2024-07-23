package forum.hub.api.domain.topico;

import forum.hub.api.domain.comentario.DadosComentario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

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



    public DadosListagemTopico(Topico topico) {
        this.id = topico.getId();
        this.titulo = topico.getTitulo();
        this.mensagem = topico.getMensagem();
        this.dataCriacao = topico.getDataCriacao();
        this.status = topico.getStatus();
        this.curso = topico.getCurso();
        this.respostas = topico.getRespostas();
        this.usuarioId = topico.getUsuario().getId();
        this.comentarios = topico.getComentarios().stream()
                .map(DadosComentario::new)
                .toList();
    }

}
