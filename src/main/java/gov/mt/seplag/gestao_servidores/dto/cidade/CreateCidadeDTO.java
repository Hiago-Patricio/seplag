package gov.mt.seplag.gestao_servidores.dto.cidade;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CreateCidadeDTO {

    @NotBlank(message = "name is required")
    @Schema(example = "Cuiabá")
    private String nome;

    @NotBlank(message = "uf is required")
    @Schema(example = "MT")
    private String uf;
}
