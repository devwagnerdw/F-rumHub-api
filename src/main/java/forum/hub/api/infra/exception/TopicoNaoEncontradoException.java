package forum.hub.api.infra.exception;

public class TopicoNaoEncontradoException extends RuntimeException {
    public TopicoNaoEncontradoException(String message) {
        super(message);
    }
}