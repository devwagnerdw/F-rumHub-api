package forum.hub.api.infra.exception;
public class UsuarioNaoAutorizadoException extends RuntimeException {
    public UsuarioNaoAutorizadoException(String message) {
        super(message);
    }
}