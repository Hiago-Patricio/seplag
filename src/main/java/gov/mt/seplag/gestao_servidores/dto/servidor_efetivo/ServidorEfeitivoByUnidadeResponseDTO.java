package gov.mt.seplag.gestao_servidores.dto.servidor_efetivo;

import lombok.Data;

@Data
public class ServidorEfeitivoByUnidadeResponseDTO {

    private String nome;

    private Integer idade;

    private String unidade;

    private String urlTemporaria;
}