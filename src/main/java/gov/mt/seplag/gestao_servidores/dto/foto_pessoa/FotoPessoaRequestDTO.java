package gov.mt.seplag.gestao_servidores.dto.foto_pessoa;

import gov.mt.seplag.gestao_servidores.dto.pessoa.PessoaRequestDTO;
import gov.mt.seplag.gestao_servidores.entity.Pessoa;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

@Data
public class FotoPessoaRequestDTO {

    @NotNull(message = "A data da foto é obrigatória")
    @Schema(example = "2023-01-01", description = "Data em que a foto foi registrada")
    private Date data;

    @NotBlank(message = "O bucket da foto é obrigatório")
    @Size(max = 50, message = "O bucket deve ter no máximo 50 caracteres")
    @Schema(example = "bucket-pessoa-fotos", description = "Nome do bucket de armazenamento da foto")
    private String bucket;

    @NotBlank(message = "O hash da foto é obrigatório")
    @Size(max = 50, message = "O hash deve ter no máximo 50 caracteres")
    @Schema(example = "abc123hash...", description = "Hash da foto para identificação no bucket")
    private String hash;

    @NotNull(message = "Pessoa é obrigatória")
    @Schema(description = "ID da pessoa associada à foto")
    private Long pessoaId;
}