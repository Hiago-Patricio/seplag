package gov.mt.seplag.gestao_servidores.exception;

public class ResourceInvalidException extends RuntimeException {
    public ResourceInvalidException(Class<?> entityClass, String fieldName, Object fieldValue) {
        super(entityClass.getSimpleName() + " not found with " + fieldName + " = " + fieldValue);
    }

    public ResourceInvalidException(String message) {
        super(message);
    }
}
