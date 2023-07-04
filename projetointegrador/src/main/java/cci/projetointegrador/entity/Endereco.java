package cci.projetointegrador.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

public class Endereco {
    @Getter @Setter
    @Column(name = "logradouro")
    private String logradouro;

    @Getter @Setter
    @Column(name = "localidade")
    private String localidade;
    @Getter @Setter
    @Column(name = "bairro")
    private String bairro;

    @Getter @Setter
    @Column(name = "logradouro")
    private String uf;
}
