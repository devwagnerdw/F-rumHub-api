package forum.hub.api.domain.comentario;

public record DadosComentario(
        String texto,
        Long usuarioId
) {
    public DadosComentario(Comentario comentario) {
        this(comentario.getTexto(), comentario.getUsuario().getId());
    }
}
