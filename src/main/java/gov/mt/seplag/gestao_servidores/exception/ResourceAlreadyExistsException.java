package gov.mt.seplag.gestao_servidores.exception;

public class ResourceAlreadyExistsException extends RuntimeException {
    public ResourceAlreadyExistsException(Class<?> entityClass, String fieldName, Object fieldValue) {
        super(entityClass.getSimpleName() + " duplicado com " + fieldName + " = " + fieldValue);
    }

    public ResourceAlreadyExistsException(String message) {
        super(message);
    }
}
