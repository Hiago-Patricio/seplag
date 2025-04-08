package gov.mt.seplag.gestao_servidores.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Class<?> entityClass, String fieldName, Object fieldValue) {
        super(entityClass.getSimpleName() + " not found with " + fieldName + " = " + fieldValue);
    }

    public NotFoundException(String message) {
        super(message);
    }
}
