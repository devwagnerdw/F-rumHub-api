package forum.hub.api.domain.comentario;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroComentario(String texto) {

        public DadosCadastroComentario(Comentario comentario){
                this(comentario.getTexto());
        }
}
