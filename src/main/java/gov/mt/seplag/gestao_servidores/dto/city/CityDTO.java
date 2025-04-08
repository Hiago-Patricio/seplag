package gov.mt.seplag.gestao_servidores.dto.city;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@ToString
public class CityDTO {
    private Long id;

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "uf is required")
    private String uf;
}
