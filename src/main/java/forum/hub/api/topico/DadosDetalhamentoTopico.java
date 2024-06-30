package forum.hub.api.topico;

import java.util.Date;

public record DadosDetalhamentoTopico(String titulo, String mensagem, Date dataCriacao, Status status, String autor, Curso curso) {

    public DadosDetalhamentoTopico(Topico topico){
        this(topico.getTitulo(),topico.getMensagem(),topico.getDataCriacao(),topico.getStatus(),topico.getAutor(),topico.getCurso());
    }
}
