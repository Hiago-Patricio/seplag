package gov.mt.seplag.gestao_servidores.exception;

public class DuplicatedResourceException extends RuntimeException {
    public DuplicatedResourceException(Class<?> entityClass, String fieldName, Object fieldValue) {
        super("Duplicated " + entityClass.getSimpleName() + " with " + fieldName + " = " + fieldValue);
    }

    public DuplicatedResourceException(String message) {
        super(message);
    }
}
