package projetointegrador.cciar.projetointegradorcciar.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name = "tb_endereco", schema = "public")
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
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO) // Gera um valor aleatório
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @OneToOne(mappedBy = "endereco") // Nome do campo em Pessoa
    private Pessoa pessoa;

    @Getter @Setter
    @Column (name = "cep")
    private String cep;
    @Getter
    @Setter
    @Column(name = "logradouro")
    private String logradouro;

    @Getter @Setter
    @Column(name = "localidade")
    private String localidade;
    @Getter @Setter
    @Column(name = "bairro")
    private String bairro;

    @Getter @Setter
    @Column (name = "n_casa")
    private int numCasa;

    @Getter @Setter
    @Column(name = "uf")
    private String uf;
}
