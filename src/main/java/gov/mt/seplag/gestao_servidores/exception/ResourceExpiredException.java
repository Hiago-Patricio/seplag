package gov.mt.seplag.gestao_servidores.exception;

public class ResourceExpiredException extends RuntimeException {
    public ResourceExpiredException(String message) {
        super(message);
    }
}
