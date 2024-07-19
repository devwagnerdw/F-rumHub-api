package forum.hub.api.domain.topico;

import java.util.Date;

public record DadosDetalhamentoTopico(String titulo, String mensagem, Date dataCriacao, Status status, Curso curso, int respostas, Long usuario_id) {

    public DadosDetalhamentoTopico(Topico topico){
        this(topico.getTitulo(),topico.getMensagem(),topico.getDataCriacao(),topico.getStatus(),topico.getCurso(), topico.getRespostas(), topico.getUsuario().getId());
    }
}
