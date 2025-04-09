package gov.mt.seplag.gestao_servidores.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pessoa")
@Data
@ToString(exclude = { "fotosPessoa", "enderecos", "servidoresTemporarios", "servidoresEfetivos", "lotacoes" })
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pes_id")
    private Long id;

    @Column(name = "pes_nome", nullable = false, length = 200)
    private String nome;

    @Column(name = "pes_data_nascimento", nullable = false)
    private Date dataNascimento;

    @Column(name = "pes_sexo", nullable = false, length = 9)
    private String sexo;

    @Column(name = "pes_mae", nullable = false, length = 200)
    private String mae;

    @Column(name = "pes_pai", nullable = false, length = 200)
    private String pai;

    @OneToMany(mappedBy = "pessoa")
    @JsonIgnore
    private List<FotoPessoa> fotosPessoa;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "pessoa_endereco",
            joinColumns = @JoinColumn(name = "pes_id"),
            inverseJoinColumns = @JoinColumn(name = "end_id")
    )
    private List<Endereco> enderecos;

    @OneToMany(mappedBy = "pessoa")
    @JsonIgnore
    private List<ServidorTemporario> servidoresTemporarios;

    @OneToMany(mappedBy = "pessoa")
    @JsonIgnore
    private List<ServidorEfetivo> servidoresEfetivos;

    @OneToMany(mappedBy = "pessoa")
    @JsonIgnore
    private List<Lotacao> lotacoes;
}