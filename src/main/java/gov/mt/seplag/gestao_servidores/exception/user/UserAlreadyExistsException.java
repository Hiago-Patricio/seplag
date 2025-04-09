package gov.mt.seplag.gestao_servidores.exception.user;

import gov.mt.seplag.gestao_servidores.entity.User;
import gov.mt.seplag.gestao_servidores.exception.ResourceAlreadyExistsException;

public class UserAlreadyExistsException extends ResourceAlreadyExistsException {
    public UserAlreadyExistsException(String username) {
        super(User.class, "username", username);
    }
}
