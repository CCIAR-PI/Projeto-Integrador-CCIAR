package projetointegrador.cciar.projetointegradorcciar.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;
import projetointegrador.cciar.projetointegradorcciar.entity.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Validated
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
    @NotNull (message = "Nome não pode ser nulo")
    @NotBlank (message = "Nome não estar ser em branco")
    @Size (max = 50, message = "Nome excede limite de caracteres")
    private String nome;
    @NotNull (message = "CPF não pode ser nulo")
    @NotBlank (message = "CPF não pode estar em branco")
    @Size (max = 14, message = "CPF excede limite de caracteres")
    private String cpf;
    private Endereco endereco;
    @NotNull (message = "Data de nascimento não pode ser nula")
    private int dataNascimento;
    @NotNull (message = "RG não pode ser nulo")
    @NotBlank (message = "RG não pode estar em branco")
    private String rg;
    @NotNull (message = "Telefone não pode ser nulo")
    @NotBlank (message = "Telefone não pode estar em branco")
    private String telefone;

    @NotNull (message = "Naturalidade não pode ser nula")
    @NotBlank (message = "Naturalidade não pode estar em branco")
    @Size (max = 50, message = "Naturalidade excede limite de caracteres")
    private String naturalidade;
    @NotNull (message = "Nacionalidade não pode ser nula")
    @NotBlank (message = "Nacionalidade não pode estar em branco")
    @Size (max = 50, message = "Nacionalidade excede limite de caracteres")
    private String nacionalidade;

    private Escolaridade escolaridade;

    private Sexo sexo;

    private String cadastroPor;

    private LocalDateTime dataCadastro;

    private LocalDateTime edicaoCadastro;

    private boolean ativo;

}
