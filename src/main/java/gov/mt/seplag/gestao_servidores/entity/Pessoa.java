package gov.mt.seplag.gestao_servidores.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "pessoa")
@Data
@ToString
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
}