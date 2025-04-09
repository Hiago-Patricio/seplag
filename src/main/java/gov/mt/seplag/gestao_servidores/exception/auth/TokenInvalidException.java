package gov.mt.seplag.gestao_servidores.exception.auth;

import gov.mt.seplag.gestao_servidores.exception.ResourceInvalidException;

public class TokenInvalidException extends ResourceInvalidException {
    public TokenInvalidException() {
        super("Invalid JWT token.");
    }
}
