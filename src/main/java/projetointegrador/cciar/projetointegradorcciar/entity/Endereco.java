package projetointegrador.cciar.projetointegradorcciar.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name = "tb_endereco", schema = "public")
@Getter @Setter
public class Endereco {
    public Endereco() {
    }

    public Endereco(Long id, String cep, String logradouro, String localidade, String bairro, int numCasa, String uf) {
        this.id = id;
        this.cep = cep;
        this.logradouro = logradouro;
        this.localidade = localidade;
        this.bairro = bairro;
        this.numCasa = numCasa;
        this.uf = uf;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Gera um valor aleatório
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @OneToOne(mappedBy = "endereco") // Nome do campo em Pessoa
    @JsonBackReference
    private Pessoa pessoa;

    @Column (name = "cep")
    private String cep;

    @Column(name = "logradouro")
    private String logradouro;

    @Column(name = "localidade")
    private String localidade;

    @Column(name = "bairro")
    private String bairro;

    @Column (name = "n_casa")
    private int numCasa;

    @Column(name = "uf")
    private String uf;
}
