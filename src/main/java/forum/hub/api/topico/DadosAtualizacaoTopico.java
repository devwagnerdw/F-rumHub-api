package forum.hub.api.topico;

import jakarta.validation.constraints.NotNull;


public record DadosAtualizacaoTopico (
        String titulo,
        String mensagem,
        String autor,
        Curso curso){


}
