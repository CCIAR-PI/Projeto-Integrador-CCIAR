package projetointegrador.cciar.projetointegradorcciar.dto;

import lombok.Getter;
import lombok.Setter;
import projetointegrador.cciar.projetointegradorcciar.entity.*;

import java.time.LocalDateTime;

@Getter
@Setter
public class PessoaDTO {
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
