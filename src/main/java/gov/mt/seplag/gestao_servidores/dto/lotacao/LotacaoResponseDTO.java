package gov.mt.seplag.gestao_servidores.dto.lotacao;

import gov.mt.seplag.gestao_servidores.entity.Pessoa;
import gov.mt.seplag.gestao_servidores.entity.Unidade;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

@Data
public class LotacaoResponseDTO {

    @Schema(example = "1", description = "Identificador único da lotação")
    private Long id;

    @Schema(example = "2023-01-01", description = "Data de início da lotação")
    private Date dataLotacao;

    @Schema(example = "2023-06-01", description = "Data de remoção da lotação, caso a lotação tenha sido removida")
    private Date dataRemocao;

    @Schema(example = "Portaria 12345", description = "Número da portaria associada à lotação")
    private String portaria;

    @Schema(description = "Pessoa associada à lotação")
    private Pessoa pessoa;

    @Schema(description = "Unidade associada à lotação")
    private Unidade unidade;
}