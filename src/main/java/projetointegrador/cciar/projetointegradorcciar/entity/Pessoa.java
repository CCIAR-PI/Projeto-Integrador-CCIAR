package projetointegrador.cciar.projetointegradorcciar.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDateTime;

@Entity
@Table(name = "Pessoas", schema = "public")
@Getter @Setter
public class Pessoa {


    public Pessoa() {
    }

    @SuppressWarnings("squid:S00107")
    public Pessoa(Long id, String nome, String cpf, Endereco endereco, int dataNascimento, String rg, String telefone, String naturalidade, String nacionalidade, Escolaridade escolaridade, Sexo sexo,boolean ativo) {
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

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO) // Gera um valor aleatório
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    @Column (name = "nome", nullable = false, length = 50)
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ\\s]*$", message = "Nome não pode conter caracteres especiais")
    @NotBlank (message = "Nome não pode ser nulo")
    @NotNull (message = "Nome não pode ser nulo")
    private String nome;
    @Column (name = "cpf", nullable = false, unique = true, length = 14)
    @CPF(message = "CPF inválido")
    @NotBlank (message = "CPF não pode ser nulo")
    private String cpf;

    @OneToOne
    @JoinColumn(name = "endereco_id") // Coluna de chave estrangeira em Pessoa
    private Endereco endereco;
    @Column (name = "data_nascimento", nullable = false)
    private int dataNascimento;
    @Column (name = "rg", nullable = false, unique = true, length = 12)
    @NotBlank (message = "RG não pode ser nulo")
    private String rg;

    @Column (name = "telefone", nullable = false, length = 17)
    @Pattern(regexp = "\\(?\\d{2,}\\)?[ -]?\\d{4,}[\\-\\s]?\\d{4}", message = "Formato de telefone inválido")
    @NotBlank (message = "Telefone não pode ser nulo")
    private String telefone;
    @Column (name = "naturalidade", nullable = false, length = 50)
    @NotBlank (message = "Naturalidade não pode ser nula")
    private String naturalidade;

    @Column (name = "nacionalidade", nullable = false, length = 50)
    @NotBlank (message = "Nacionalidade não pode ser nula")
    private String nacionalidade;

    @Enumerated (EnumType.STRING)
    @Column (name = "escolaridade", nullable = false)
    private Escolaridade escolaridade;

    @Enumerated (EnumType.STRING)
    @Column (name = "sexo", nullable = false)
    private Sexo sexo;

    @Column (name = "cadastro_por")
    private String cadastroPor;

    @Column (name = "cadastro_data")
    private LocalDateTime dataCadastro;
    @Column (name = "edicao_cadastro")
    private LocalDateTime edicaoCadastro;

    @Column (name = "ativo", nullable = false)
    private boolean ativo;

    @PrePersist
    private void prePersist ()
    {
        this.dataCadastro = LocalDateTime.now();
        this.ativo = true;
    }

    @PreUpdate
    private void preUpdate ()
    {
        this.edicaoCadastro = LocalDateTime.now();
    }
}
