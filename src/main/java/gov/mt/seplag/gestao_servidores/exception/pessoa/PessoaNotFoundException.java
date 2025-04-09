package gov.mt.seplag.gestao_servidores.exception.pessoa;

import gov.mt.seplag.gestao_servidores.entity.Pessoa;
import gov.mt.seplag.gestao_servidores.exception.ResourceNotFoundException;

public class PessoaNotFoundException extends ResourceNotFoundException {
    public PessoaNotFoundException(Long id) {
        super(Pessoa.class, "id", id);
    }
}