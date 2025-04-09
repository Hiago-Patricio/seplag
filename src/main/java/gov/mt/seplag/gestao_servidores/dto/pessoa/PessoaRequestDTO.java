package gov.mt.seplag.gestao_servidores.dto.pessoa;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Data
public class PessoaRequestDTO {

    @NotBlank(message = "O nome é obrigatório")
    @Schema(example = "João Silva", description = "Nome completo da pessoa")
    private String nome;

    @NotNull(message = "A data de nascimento é obrigatória")
    @Schema(example = "1990-01-01", description = "Data de nascimento da pessoa no formato yyyy-MM-dd")
    private Date dataNascimento;

    @NotBlank(message = "O sexo é obrigatório")
    @Size(max = 9, message = "O sexo deve ter no máximo 9 caracteres")
    @Schema(example = "Masculino", description = "Sexo da pessoa (até 9 caracteres, ex.: Masculino, Feminino, Outro)")
    private String sexo;

    @NotBlank(message = "O nome da mãe é obrigatório")
    @Schema(example = "Maria Silva", description = "Nome da mãe da pessoa")
    private String mae;

    @NotBlank(message = "O nome do pai é obrigatório")
    @Schema(example = "José Silva", description = "Nome do pai da pessoa")
    private String pai;
}