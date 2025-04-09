package gov.mt.seplag.gestao_servidores.exception.auth;

import gov.mt.seplag.gestao_servidores.exception.ResourceExpiredException;

public class TokenExpiredException extends ResourceExpiredException {
    public TokenExpiredException() {
        super("Token has expired and cannot be refreshed.");
    }
}
