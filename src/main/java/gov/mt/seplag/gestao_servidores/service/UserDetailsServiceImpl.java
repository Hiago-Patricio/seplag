package gov.mt.seplag.gestao_servidores.service;

import gov.mt.seplag.gestao_servidores.exception.user.UserAlreadyExistsException;
import gov.mt.seplag.gestao_servidores.exception.user.UserNotFoundException;
import gov.mt.seplag.gestao_servidores.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UserNotFoundException {
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        return User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities("USER") // Simples por enquanto
                .build();
    }
}
