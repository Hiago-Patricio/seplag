package gov.mt.seplag.gestao_servidores.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
        import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "unidade")
@Data
@ToString(exclude = { "lotacoes", "enderecos" })
public class Unidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unid_id")
    private Long id;

    @Column(name = "unid_nome", nullable = false, length = 200)
    private String nome;

    @Column(name = "unid_sigla", nullable = false, length = 20)
    private String uf;

    @OneToMany(mappedBy = "unidade", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Lotacao> lotacoes;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "unidade_endereco",
            joinColumns = @JoinColumn(name = "unid_id"),
            inverseJoinColumns = @JoinColumn(name = "end_id")
    )
    private List<Endereco> enderecos;
}
