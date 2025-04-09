package gov.mt.seplag.gestao_servidores.dto.endereco;

import gov.mt.seplag.gestao_servidores.dto.cidade.CidadeResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EnderecoResponseDTO {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "Rua", description = "Tipo do logradouro, por exemplo: Rua, Avenida, Travessa")
    private String tipoLogradouro;

    @Schema(example = "Rua das Palmeiras", description = "Nome do logradouro")
    private String logradouro;

    @Schema(example = "123", description = "Número do endereço")
    private int numero;

    @Schema(example = "Centro", description = "Bairro do endereço")
    private String bairro;

    @Schema(description = "Informações da cidade relacionada ao endereço")
    private CidadeResponseDTO cidade;
}