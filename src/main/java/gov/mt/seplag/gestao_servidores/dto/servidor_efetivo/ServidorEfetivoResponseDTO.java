package gov.mt.seplag.gestao_servidores.dto.servidor_efetivo;

import gov.mt.seplag.gestao_servidores.entity.Pessoa;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
public class ServidorEfetivoResponseDTO {

    @Schema(description = "Entidade da pessoa associada ao servidor efetivo")
    private Pessoa pessoa;

    @Schema(example = "MAT12345", description = "Matrícula do servidor efetivo")
    private String matricula;
}