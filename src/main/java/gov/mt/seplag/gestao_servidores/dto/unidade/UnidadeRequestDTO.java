package gov.mt.seplag.gestao_servidores.dto.unidade;

import gov.mt.seplag.gestao_servidores.dto.endereco.EnderecoRequestDTO;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Data
public class UnidadeRequestDTO {

    @NotBlank(message = "O nome da unidade é obrigatório")
    @Schema(example = "Secretaria de Educação", description = "Nome completo da unidade")
    private String nome;

    @NotBlank(message = "A sigla da unidade é obrigatória")
    @Size(max = 20, message = "A sigla da unidade deve ter no máximo 20 caracteres")
    @Schema(example = "SEDUC", description = "Sigla da unidade")
    private String uf;

    private List<EnderecoRequestDTO> enderecos;
}