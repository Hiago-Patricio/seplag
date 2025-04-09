package gov.mt.seplag.gestao_servidores.service;

import gov.mt.seplag.gestao_servidores.dto.auth.*;
import gov.mt.seplag.gestao_servidores.entity.User;
import gov.mt.seplag.gestao_servidores.exception.auth.InvalidCredentialsException;
import gov.mt.seplag.gestao_servidores.exception.auth.TokenExpiredException;
import gov.mt.seplag.gestao_servidores.exception.auth.TokenInvalidException;
import gov.mt.seplag.gestao_servidores.exception.user.UserAlreadyExistsException;
import gov.mt.seplag.gestao_servidores.exception.user.UserNotFoundException;
import gov.mt.seplag.gestao_servidores.repository.UserRepository;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserDetailsServiceImpl userDetailsService;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(AuthRequest request) {
        log.info("Attempting to authenticate user: {}", request.getUsername());

        UserDetails user;
        try {
            user = userDetailsService.loadUserByUsername(request.getUsername());
        } catch (UsernameNotFoundException e) {
            log.warn("User not found: {}", request.getUsername());
            throw new UserNotFoundException(request.getUsername());
        }

        var authToken = new UsernamePasswordAuthenticationToken(
                request.getUsername(), request.getPassword());

        try {
            authenticationManager.authenticate(authToken);
            log.info("User {} successfully authenticated", request.getUsername());
        } catch (BadCredentialsException e) {
            log.warn("Invalid credentials for user: {}", request.getUsername());
            throw new InvalidCredentialsException();
        }

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        log.debug("Generated tokens for user {}: accessToken={}, refreshToken={}",
                request.getUsername(), accessToken, refreshToken);

        return new AuthResponse(accessToken, refreshToken);
    }

    public AuthResponse refreshToken(String token) {
        log.info("Refresh token request received");

        try {
            if (!"refresh".equals(jwtService.extractTokenType(token))) {
                log.warn("Invalid refresh token: {}", token);
                throw new TokenInvalidException();
            }

            if (jwtService.isTokenExpired(token)) {
                log.warn("Token is expired");
                throw new TokenExpiredException();
            }

            String username = jwtService.extractUsername(token);
            log.info("Username extracted from token: {}", username);

            UserDetails user = userDetailsService.loadUserByUsername(username);

            if (!jwtService.isTokenValid(token, user)) {
                log.warn("Token is invalid for user: {}", username);
                throw new TokenInvalidException();
            }

            String newAccessToken = jwtService.generateAccessToken(user);
            String newRefreshToken = jwtService.generateRefreshToken(user);

            log.info("Tokens successfully refreshed for user: {}", username);
            log.debug("New accessToken={}, new refreshToken={}", newAccessToken, newRefreshToken);

            return new AuthResponse(newAccessToken, newRefreshToken);
        } catch (JwtException e) {
            log.error("Failed to validate token: {}", e.getMessage());
            throw new TokenInvalidException();
        }
    }

    public void register(AuthRequest request) {
        log.info("Registering new user: {}", request.getUsername());

        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            log.warn("Username already exists: {}", request.getUsername());
            throw new UserAlreadyExistsException(request.getUsername());
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userRepository.save(user);
        log.info("User successfully registered: {}", request.getUsername());
    }
}
