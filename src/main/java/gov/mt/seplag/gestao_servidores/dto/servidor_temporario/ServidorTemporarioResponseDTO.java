package gov.mt.seplag.gestao_servidores.dto.servidor_temporario;

import gov.mt.seplag.gestao_servidores.entity.Pessoa;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
public class ServidorTemporarioResponseDTO {

    @Schema(description = "Entidade da pessoa associada ao servidor temporário")
    private Pessoa pessoa;

    @Schema(example = "2023-04-01", description = "Data de admissão do servidor temporário")
    private Date dataAdmissao;

    @Schema(example = "2023-09-01", description = "Data de demissão do servidor temporário")
    private Date dataDemissao;
}