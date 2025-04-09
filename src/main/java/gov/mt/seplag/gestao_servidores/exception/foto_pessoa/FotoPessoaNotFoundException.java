package gov.mt.seplag.gestao_servidores.exception.foto_pessoa;

import gov.mt.seplag.gestao_servidores.entity.FotoPessoa;
import gov.mt.seplag.gestao_servidores.exception.ResourceNotFoundException;

public class FotoPessoaNotFoundException extends ResourceNotFoundException {
    public FotoPessoaNotFoundException(Long id) {
        super(FotoPessoa.class, "id", id);
    }
}