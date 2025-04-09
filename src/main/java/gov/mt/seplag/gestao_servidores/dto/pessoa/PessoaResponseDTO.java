package gov.mt.seplag.gestao_servidores.dto.pessoa;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
public class PessoaResponseDTO {

    @Schema(example = "1", description = "ID da pessoa")
    private Long id;

    @Schema(example = "João Silva", description = "Nome completo da pessoa")
    private String nome;

    @Schema(example = "1990-01-01", description = "Data de nascimento da pessoa")
    private Date dataNascimento;

    @Schema(example = "Masculino", description = "Sexo da pessoa (até 9 caracteres, ex.: Masculino, Feminino, Outro)")
    private String sexo;

    @Schema(example = "Maria Silva", description = "Nome da mãe da pessoa")
    private String mae;

    @Schema(example = "José Silva", description = "Nome do pai da pessoa")
    private String pai;
}