package forum.hub.api.topico;

import java.util.Date;

public record DadosListagemTopico(Long id, String titulo, String mensagem, Date dataCriacao, Status status, String autor, Curso curso) {


    public DadosListagemTopico(Topico topico){
        this(topico.getId(), topico.getTitulo(),topico.getMensagem(),topico.getDataCriacao(),topico.getStatus(),topico.getAutor(),topico.getCurso());
    }
}
