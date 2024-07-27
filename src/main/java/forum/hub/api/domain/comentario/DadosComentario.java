package forum.hub.api.domain.comentario;

public record DadosComentario(
        Long id,
        String texto,
        Long usuarioId
) {
    public DadosComentario(Comentario comentario) {
        this( comentario.getId(), comentario.getTexto(), comentario.getUsuario().getId());
    }
}
