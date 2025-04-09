package gov.mt.seplag.gestao_servidores.exception.servidor_temporario;

import gov.mt.seplag.gestao_servidores.entity.ServidorTemporario;
import gov.mt.seplag.gestao_servidores.exception.ResourceNotFoundException;

public class ServidorTemporarioNotFoundException extends ResourceNotFoundException {
    public ServidorTemporarioNotFoundException(Long id) {
        super(ServidorTemporario.class, "id", id);
    }
}