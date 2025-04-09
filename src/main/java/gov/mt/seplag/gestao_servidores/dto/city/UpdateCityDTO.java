package gov.mt.seplag.gestao_servidores.dto.city;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UpdateCityDTO {

    @NotBlank(message = "name is required")
    @Schema(description = "City name", example = "Cuiabá")
    private String name;

    @NotBlank(message = "uf is required")
    @Schema(description = "Federative unit (UF)", example = "MT")
    private String uf;
}
