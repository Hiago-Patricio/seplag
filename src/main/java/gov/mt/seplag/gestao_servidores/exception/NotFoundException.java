package gov.mt.seplag.gestao_servidores.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Class<?> entityClass, Long id) {
        super(entityClass.getSimpleName() + " not found with ID: " + id);
    }
}
