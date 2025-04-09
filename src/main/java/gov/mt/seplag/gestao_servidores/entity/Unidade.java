package gov.mt.seplag.gestao_servidores.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
        import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "unidade")
@Data
@ToString
public class Unidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unid_id")
    private Long id;

    @Column(name = "unid_nome", nullable = false, length = 200)
    private String nome;

    @Column(name = "unid_sigla", nullable = false, length = 20)
    private String uf;
}
