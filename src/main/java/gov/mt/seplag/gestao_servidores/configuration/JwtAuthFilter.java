package gov.mt.seplag.gestao_servidores.configuration;

import gov.mt.seplag.gestao_servidores.exception.auth.TokenNotFoundException;
import gov.mt.seplag.gestao_servidores.service.JwtService;
import gov.mt.seplag.gestao_servidores.service.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getServletPath();

        // Permite requisições públicas sem autenticação
        if (path.startsWith("/api/auth")
                || path.startsWith("/v3/api-docs")
                || path.startsWith("/swagger-ui")
                || path.equals("/swagger-ui.html")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            log.warn("Missing or invalid Authorization header");
            throw new TokenNotFoundException();
        }

        final String token = jwtService.extractTokenFromHeader(authHeader);

        String username;
        try {
            username = jwtService.extractUsername(token);
        } catch (Exception e) {
            log.warn("Invalid token: {}", e.getMessage());
            filterChain.doFilter(request, response);
            return;
        }

        String tokenType = jwtService.extractTokenType(token);
        if (!"access".equals(tokenType)) {
            log.warn("Blocked token of type '{}': only 'access' tokens are allowed for protected endpoints.", tokenType);
            filterChain.doFilter(request, response);
            return;
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            var userDetails = userDetailsService.loadUserByUsername(username);

            if (jwtService.isTokenValid(token, userDetails)) {
                var authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
                log.info("Authenticated user '{}' with access token", username);
            } else {
                log.warn("Invalid token for user '{}'", username);
            }
        }

        filterChain.doFilter(request, response);
    }
}
