package gov.mt.seplag.gestao_servidores.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthRequest {
    @NotBlank(message = "username is required")
    @Schema(example = "joao_das_neves")
    private String username;

    @NotBlank(message = "password is required")
    @Schema(example = "12345678")
    private String password;
}
