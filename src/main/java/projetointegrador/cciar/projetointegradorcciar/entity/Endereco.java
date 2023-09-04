package projetointegrador.cciar.projetointegradorcciar.entity;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

public class Endereco {

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
    @Column(name = "logradouro")
    private String uf;

}
