package gov.mt.seplag.gestao_servidores.entity;

import jakarta.persistence.*;
        import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "foto_pessoa")
@Data
@ToString
public class FotoPessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fp_id")
    private Long id;

    @Column(name = "fp_data", nullable = false)
    private Date data;

    @Column(name = "fp_bucket", nullable = false, length = 50)
    private String bucket;

    @Column(name = "fp_hash", nullable = false, length = 50)
    private String hash;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pes_id", nullable = false)
    private Pessoa pessoa;
}