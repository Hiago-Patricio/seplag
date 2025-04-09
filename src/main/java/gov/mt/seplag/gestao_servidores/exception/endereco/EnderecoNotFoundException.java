package gov.mt.seplag.gestao_servidores.exception.endereco;

import gov.mt.seplag.gestao_servidores.entity.Endereco;
import gov.mt.seplag.gestao_servidores.exception.ResourceNotFoundException;

public class EnderecoNotFoundException extends ResourceNotFoundException {
    public EnderecoNotFoundException(Long id) {
        super(Endereco.class, "id", id);
    }
}
