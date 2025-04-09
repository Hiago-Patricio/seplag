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
    @ManyToOne(optional = false)
    @JoinColumn(name = "pes_id", nullable = false)
    private Pessoa pessoa;

    @Column(name = "st_data_admissao", nullable = false)
    private Date dataAdmissao;

    @Column(name = "st_data_demissao", nullable = true)
    private Date dataDemissao;
}
