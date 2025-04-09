package gov.mt.seplag.gestao_servidores.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "lotacao")
@Data
@ToString
public class Lotacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lot_id")
    private Long id;

    @Column(name = "lot_data_lotacao", nullable = false)
    private Date dataLotacao;

    @Column(name = "lot_data_remocao")
    private Date dataRemocao;

    @Column(name = "lot_portaria", length = 100)
    private String portaria;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pes_id", nullable = false)
    private Pessoa pessoa;

    @ManyToOne(optional = false)
    @JoinColumn(name = "unid_id", nullable = false)
    private Unidade unidade;
}
