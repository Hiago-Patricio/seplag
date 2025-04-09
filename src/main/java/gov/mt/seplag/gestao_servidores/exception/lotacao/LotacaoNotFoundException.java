package gov.mt.seplag.gestao_servidores.exception.lotacao;

import gov.mt.seplag.gestao_servidores.entity.Lotacao;
import gov.mt.seplag.gestao_servidores.exception.ResourceNotFoundException;

public class LotacaoNotFoundException extends ResourceNotFoundException {
    public LotacaoNotFoundException(Long id) {
        super(Lotacao.class, "id", id);
    }
}