package projetointegrador.cciar.projetointegradorcciar.dto;

import lombok.Getter;
import lombok.Setter;
import projetointegrador.cciar.projetointegradorcciar.entity.*;

import java.time.LocalDateTime;

@Getter
@Setter
public class PessoaDTO {

    public PessoaDTO() {
    }

    @SuppressWarnings("squid:S00107")
    public PessoaDTO(Long id, String nome, String cpf, Endereco endereco, int dataNascimento, String rg, String telefone, String naturalidade, String nacionalidade, Escolaridade escolaridade, Sexo sexo,boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.dataNascimento = dataNascimento;
        this.rg = rg;
        this.telefone = telefone;
        this.naturalidade = naturalidade;
        this.nacionalidade = nacionalidade;
        this.escolaridade = escolaridade;
        this.sexo = sexo;
        this.ativo = ativo;
    }


    private Long id;
    private String nome;
    private String cpf;
    private Endereco endereco;
    private int dataNascimento;
    private String rg;

    private String telefone;

    private String naturalidade;

    private String nacionalidade;

    private Escolaridade escolaridade;

    private Sexo sexo;

    private String cadastroPor;

    private LocalDateTime dataCadastro;

    private LocalDateTime edicaoCadastro;

    private boolean ativo;

}
