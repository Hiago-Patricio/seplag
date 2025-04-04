package gov.mt.seplag.gestao_servidores.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cidade")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String name;

    private String uf;

    public City(Long id, String name, String uf) {
        this.id = id;
        this.name = name;
        this.uf = uf;
    }

    public City() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String nome) {
        this.name = nome;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}
