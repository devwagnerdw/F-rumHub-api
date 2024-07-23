package forum.hub.api.domain.topico;


public record DadosAtualizacaoTopico (
        String titulo,
        String mensagem,
        Status status,
        Curso curso){
}
