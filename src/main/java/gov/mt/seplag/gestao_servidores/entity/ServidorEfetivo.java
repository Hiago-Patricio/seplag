package gov.mt.seplag.gestao_servidores.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "servidor_efetivo")
@Data
@ToString
public class ServidorEfetivo {
    @Id
    @Column(name = "pes_id")
    private Long pessoaId;

    @ManyToOne(optional = false)
    @MapsId("pessoaId")
    @JoinColumn(name = "pes_id", foreignKey = @ForeignKey(name = "fk_servidor_efetivo_pessoa"), nullable = false)
    private Pessoa pessoa;

    @Column(name = "se_matricula", length = 20)
    private String matricula;
}
