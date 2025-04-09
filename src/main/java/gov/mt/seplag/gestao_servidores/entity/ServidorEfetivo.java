package gov.mt.seplag.gestao_servidores.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "servidor_temporario")
@Data
@ToString
public class ServidorEfetivo {
    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "pes_id", nullable = false)
    private Pessoa pessoa;

    @Column(name = "se_matricula", length = 20)
    private String matricula;
}
