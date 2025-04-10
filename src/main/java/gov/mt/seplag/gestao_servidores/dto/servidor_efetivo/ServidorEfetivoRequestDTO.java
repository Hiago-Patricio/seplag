package gov.mt.seplag.gestao_servidores.dto.servidor_efetivo;

import gov.mt.seplag.gestao_servidores.dto.pessoa.PessoaRequestDTO;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
public class ServidorEfetivoRequestDTO {
    @NotBlank(message = "A matrícula é obrigatória")
    @Size(max = 20, message = "A matrícula deve ter no máximo 20 caracteres")
    @Schema(example = "MAT12345", description = "Matrícula do servidor efetivo")
    private String matricula;

    @NotNull(message = "Pessoa é obrigatória")
    @Schema(description = "Pessoa relacionada ao cadastro de servidor")
    private PessoaRequestDTO pessoa;
}