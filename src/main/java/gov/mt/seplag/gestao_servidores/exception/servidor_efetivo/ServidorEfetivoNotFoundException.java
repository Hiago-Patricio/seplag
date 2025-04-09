package gov.mt.seplag.gestao_servidores.exception.servidor_efetivo;

import gov.mt.seplag.gestao_servidores.entity.ServidorEfetivo;
import gov.mt.seplag.gestao_servidores.exception.ResourceNotFoundException;

public class ServidorEfetivoNotFoundException extends ResourceNotFoundException {
    public ServidorEfetivoNotFoundException(Long id) {
        super(ServidorEfetivo.class, "id", id);
    }
}