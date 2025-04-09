package gov.mt.seplag.gestao_servidores.dto.servidor_efetivo;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
public class ServidorEfetivoRequestDTO {

    @NotNull(message = "A pessoa associada ao servidor efetivo é obrigatória")
    @Schema(description = "ID da pessoa associada ao servidor efetivo")
    private Long pessoaId;

    @NotBlank(message = "A matrícula é obrigatória")
    @Size(max = 20, message = "A matrícula deve ter no máximo 20 caracteres")
    @Schema(example = "MAT12345", description = "Matrícula do servidor efetivo")
    private String matricula;
}