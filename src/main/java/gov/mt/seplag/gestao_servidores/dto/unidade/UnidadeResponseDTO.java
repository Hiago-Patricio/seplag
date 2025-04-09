package gov.mt.seplag.gestao_servidores.dto.unidade;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
public class UnidadeResponseDTO {

    @Schema(example = "1", description = "Identificador único da unidade")
    private Long id;

    @Schema(example = "Secretaria de Educação", description = "Nome completo da unidade")
    private String nome;

    @Schema(example = "SEDUC", description = "Sigla da unidade")
    private String uf;
}