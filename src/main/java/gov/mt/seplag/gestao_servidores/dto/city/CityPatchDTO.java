package gov.mt.seplag.gestao_servidores.dto.city;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@ToString
public class CityPatchDTO {
    private String name;

    private String uf;
}
