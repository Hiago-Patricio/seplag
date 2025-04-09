package gov.mt.seplag.gestao_servidores.dto.lotacao;

import gov.mt.seplag.gestao_servidores.entity.Pessoa;
import gov.mt.seplag.gestao_servidores.entity.Unidade;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

@Data
public class LotacaoRequestDTO {

    @NotNull(message = "A data de lotação é obrigatória")
    @Schema(example = "2023-01-01", description = "Data de início da lotação")
    private Date dataLotacao;

    @Schema(example = "2023-06-01", description = "Data de remoção da lotação, caso a lotação tenha sido removida")
    private Date dataRemocao;

    @Size(max = 100, message = "A portaria deve ter no máximo 100 caracteres")
    @Schema(example = "Portaria 12345", description = "Número da portaria associada à lotação")
    private String portaria;

    @NotNull(message = "A pessoa associada à lotação é obrigatória")
    @Schema(description = "Pessoa associada à lotação")
    private Pessoa pessoa;

    @NotNull(message = "A unidade associada à lotação é obrigatória")
    @Schema(description = "Unidade associada à lotação")
    private Unidade unidade;
}