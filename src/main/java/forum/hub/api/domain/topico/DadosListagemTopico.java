package forum.hub.api.domain.topico;

import java.util.Date;

public record DadosListagemTopico(
        Long id,
        String titulo,
        String mensagem,
        Date dataCriacao,
        Status status,Curso curso,
        int respostas,

        Long usuario_id

) {


    public DadosListagemTopico(Topico topico){
        this(topico.getId(), topico.getTitulo(),topico.getMensagem(),topico.getDataCriacao(),topico.getStatus(),topico.getCurso(), topico.getRespostas(),topico.getUsuario().getId());
    }
}
