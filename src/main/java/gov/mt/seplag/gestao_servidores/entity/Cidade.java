package gov.mt.seplag.gestao_servidores.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "cidade")
@Data
@ToString
public class Cidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cid_id")
    private Long id;

    @Column(name = "cid_nome", nullable = false)
    private String nome;

    @Column(name = "cid_uf", nullable = false)
    private String uf;

    @OneToMany(mappedBy = "cidade")
    @JsonIgnore
    private List<Endereco> enderecos;
}
