package forum.hub.api.infra.exception;

public class TopicoFechadoException extends RuntimeException {
    public TopicoFechadoException(String message) {
        super(message);
    }
}