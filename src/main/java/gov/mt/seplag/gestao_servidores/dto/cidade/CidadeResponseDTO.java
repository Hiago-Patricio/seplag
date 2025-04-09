package gov.mt.seplag.gestao_servidores.dto.cidade;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CidadeResponseDTO {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "Cuiabá")
    private String nome;

    @Schema(example = "MT")
    private String uf;
}
