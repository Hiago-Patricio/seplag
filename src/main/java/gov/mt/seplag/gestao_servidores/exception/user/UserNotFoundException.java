package gov.mt.seplag.gestao_servidores.exception.user;

import gov.mt.seplag.gestao_servidores.entity.User;
import gov.mt.seplag.gestao_servidores.exception.ResourceNotFoundException;

public class UserNotFoundException extends ResourceNotFoundException {
    public UserNotFoundException(String username) {
        super(User.class, "username", username);
    }
}
