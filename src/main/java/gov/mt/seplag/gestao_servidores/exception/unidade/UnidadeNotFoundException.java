package gov.mt.seplag.gestao_servidores.exception.unidade;

import gov.mt.seplag.gestao_servidores.entity.Unidade;
import gov.mt.seplag.gestao_servidores.exception.ResourceNotFoundException;

public class UnidadeNotFoundException extends ResourceNotFoundException {
    public UnidadeNotFoundException(Long id) {
        super(Unidade.class, "id", id);
    }
}