package gov.mt.seplag.gestao_servidores.dto.servidor_temporario;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class ServidorTemporarioRequestDTO {

    @NotNull(message = "O ID da pessoa associada é obrigatório")
    @Schema(description = "ID da pessoa associada ao servidor temporário")
    private Long pessoaId;

    @NotNull(message = "A data de admissão é obrigatória")
    @Schema(example = "2023-04-01", description = "Data de admissão do servidor temporário")
    private Date dataAdmissao;

    @Schema(example = "2023-09-01", description = "Data de demissão do servidor temporário")
    private Date dataDemissao;
}