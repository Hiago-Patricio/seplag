package gov.mt.seplag.gestao_servidores.dto.foto_pessoa;

import gov.mt.seplag.gestao_servidores.dto.pessoa.PessoaResponseDTO;
import gov.mt.seplag.gestao_servidores.entity.Pessoa;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

@Data
public class FotoPessoaResponseDTO {

    @Schema(example = "1", description = "Identificador único da foto")
    private Long id;

    @Schema(example = "2023-01-01", description = "Data em que a foto foi registrada")
    private Date data;

    @Schema(example = "bucket-pessoa-fotos", description = "Nome do bucket de armazenamento da foto")
    private String bucket;

    @Schema(example = "abc123hash...", description = "Hash da foto para identificação no bucket")
    private String hash;

    @Schema(description = "Entidade da pessoa associada à foto")
    private PessoaResponseDTO pessoa;
}