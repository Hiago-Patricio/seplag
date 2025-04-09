package gov.mt.seplag.gestao_servidores.exception.auth;

import gov.mt.seplag.gestao_servidores.exception.ResourceNotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;

public class TokenNotFoundException extends ResourceNotFoundException {
    public TokenNotFoundException() {
        super("Token not found.");
    }
}
