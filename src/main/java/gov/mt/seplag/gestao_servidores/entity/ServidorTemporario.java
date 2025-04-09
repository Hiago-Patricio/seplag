package gov.mt.seplag.gestao_servidores.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "servidor_temporario")
@Data
@ToString
public class ServidorTemporario {
    @Id
    @Column(name = "pes_id")
    private Long pessoaId;

    @ManyToOne(optional = false)
    @MapsId("pessoaId")
    @JoinColumn(name = "pes_id", foreignKey = @ForeignKey(name = "fk_servidor_temporario_pessoa"), nullable = false)
    private Pessoa pessoa;

    @Column(name = "st_data_admissao", nullable = false)
    private Date dataAdmissao;

    @Column(name = "st_data_demissao")
    private Date dataDemissao;
}
