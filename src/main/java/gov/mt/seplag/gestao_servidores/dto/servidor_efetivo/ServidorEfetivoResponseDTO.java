package gov.mt.seplag.gestao_servidores.dto.servidor_efetivo;

import gov.mt.seplag.gestao_servidores.dto.lotacao.LotacaoResponseDTO;
import gov.mt.seplag.gestao_servidores.dto.pessoa.PessoaResponseDTO;
import gov.mt.seplag.gestao_servidores.entity.Lotacao;
import gov.mt.seplag.gestao_servidores.entity.Pessoa;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Data
public class ServidorEfetivoResponseDTO {

    @Schema(description = "Entidade da pessoa associada ao servidor efetivo")
    private PessoaResponseDTO pessoa;

    @Schema(example = "MAT12345", description = "Matrícula do servidor efetivo")
    private String matricula;

    private List<LotacaoResponseDTO> lotacoes;
}