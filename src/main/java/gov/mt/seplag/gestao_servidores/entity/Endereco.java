package gov.mt.seplag.gestao_servidores.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "endereco")
@Data
@ToString
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "end_id")
    private Long id;

    @Column(name = "end_tipo_logradouro", nullable = false, length = 50)
    private String tipoLogradouro;

    @Column(name = "end_logradouro", nullable = false, length = 200)
    private String logradouro;

    @Column(name = "end_numero", nullable = false)
    private int numero;

    @Column(name = "end_bairro", nullable = false, length = 100)
    private String bairro;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cid_id", nullable = false)
    private Cidade cidade;
}
