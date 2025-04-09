package gov.mt.seplag.gestao_servidores.dto.servidor_temporario;

import gov.mt.seplag.gestao_servidores.dto.pessoa.PessoaRequestDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class ServidorTemporarioRequestDTO {
    @NotNull(message = "A data de admissão é obrigatória")
    @Schema(example = "2023-04-01", description = "Data de admissão do servidor temporário")
    private Date dataAdmissao;

    @Schema(example = "2023-09-01", description = "Data de demissão do servidor temporário")
    private Date dataDemissao;

    @NotNull(message = "Pessoa é obrigatória")
    @Schema(description = "Pessoa relacionada ao cadastro de servidor")
    private PessoaRequestDTO pessoa;
}