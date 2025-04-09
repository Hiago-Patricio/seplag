package gov.mt.seplag.gestao_servidores.exception;

public class ResourceExpiredException extends RuntimeException {
    public ResourceExpiredException(Class<?> entityClass, String fieldName, Object fieldValue) {
        super(entityClass.getSimpleName() + " not found with " + fieldName + " = " + fieldValue);
    }

    public ResourceExpiredException(String message) {
        super(message);
    }
}
