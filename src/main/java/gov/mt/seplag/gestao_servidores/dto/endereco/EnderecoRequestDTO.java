package gov.mt.seplag.gestao_servidores.dto.endereco;

import gov.mt.seplag.gestao_servidores.dto.cidade.CidadeRequestDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EnderecoRequestDTO {

    @NotNull(message = "Tipo do logradouro é obrigatório")
    @Schema(example = "Rua", description = "Tipo do logradouro, por exemplo: Rua, Avenida, Travessa")
    private String tipoLogradouro;

    @NotNull(message = "Logradouro é obrigatório")
    @Schema(example = "Rua das Palmeiras", description = "Nome do logradouro")
    private String logradouro;

    @NotNull(message = "Número é obrigatório")
    @Schema(example = "123", description = "Número do endereço")
    private int numero;

    @NotNull(message = "Bairro é obrigatório")
    @Schema(example = "Centro", description = "Bairro do endereço")
    private String bairro;

    @NotNull(message = "Cidade é obrigatória")
    @Schema(description = "Cidade relacionada ao endereço")
    private CidadeRequestDTO cidade;
}