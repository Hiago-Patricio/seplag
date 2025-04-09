package gov.mt.seplag.gestao_servidores.exception;

public class ResourceAlreadyExistsException extends RuntimeException {
    public ResourceAlreadyExistsException(Class<?> entityClass, String fieldName, Object fieldValue) {
        super("Duplicated " + entityClass.getSimpleName() + " with " + fieldName + " = " + fieldValue);
    }

    public ResourceAlreadyExistsException(String message) {
        super(message);
    }
}
