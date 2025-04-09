package gov.mt.seplag.gestao_servidores.exception.cidade;

import gov.mt.seplag.gestao_servidores.entity.Cidade;
import gov.mt.seplag.gestao_servidores.exception.ResourceNotFoundException;

public class CidadeNotFoundException extends ResourceNotFoundException {
    public CidadeNotFoundException(Long id) {
        super(Cidade.class, "id", id);
    }
}
