package gov.mt.seplag.gestao_servidores.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String AccessToken;

    private String refreshToken;
}
