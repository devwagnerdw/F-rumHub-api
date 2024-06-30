package forum.hub.api.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

public record DadosCadastroTopico(
        @NotBlank
        String titulo,
        @NotBlank
        @Size(min = 10, max = 300, message = "min de 10")
        String mensagem,
        @NotNull
        String autor,
        @NotNull
        Curso curso) {
}
