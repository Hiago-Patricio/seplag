package gov.mt.seplag.gestao_servidores.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(Class<?> entityClass, String fieldName, Object fieldValue) {
        super(entityClass.getSimpleName() + " não localizado(a) com " + fieldName + " = " + fieldValue);
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
