package projetointegrador.cciar.projetointegradorcciar.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Entity
@Table(name = "Pessoas", schema = "public")
@RequestMapping("/api/pessoa")
public class Pessoa {
    @Id
    @Getter
    @GeneratedValue (strategy = GenerationType.AUTO) // Gera um valor aleatório
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    @Getter @Setter
    @Column (name = "nome", nullable = false, length = 50)
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ\\s]*$", message = "Nome não pode conter caracteres especiais")
    @NotBlank (message = "Nome não pode ser nulo")
    private String nome;
    @Getter @Setter
    @Column (name = "cpf", nullable = false, unique = true, length = 14)
    @CPF(message = "CPF inválido")
    @NotBlank (message = "CPF não pode ser nulo")
    private String cpf;

    @Getter @Setter
    @Column (name = "cep")
    private Endereco endereco;
    @Getter @Setter
    @Column (name = "data_nascimento", nullable = false)
    private int dataNascimento;
    @Getter @Setter
    @Column (name = "rg", nullable = false, unique = true, length = 12)
    @NotBlank (message = "RG não pode ser nulo")
    private String rg;

//    @Getter @Setter
//    @Column (name = "hash_imagem", nullable = false, unique = true)
//    private String hashImg;

    @Getter @Setter
    @Column (name = "telefone", nullable = false, length = 17)
    @Pattern(regexp = "\\(?\\d{2,}\\)?[ -]?\\d{4,}[\\-\\s]?\\d{4}", message = "Formato de telefone inválido")
    @NotBlank (message = "Telefone não pode ser nulo")
    private String telefone;
    @Getter @Setter
    @Column (name = "naturalidade", nullable = false, length = 50)
    @NotBlank (message = "Naturalidade não pode ser nula")
    private String naturalidade;

    @Getter @Setter
    @Column (name = "nacionalidade", nullable = false, length = 50)
    @NotBlank (message = "Nacionalidade não pode ser nula")
    private String nacionalidade;

    @Enumerated (EnumType.STRING)
    @Getter @Setter
    @Column (name = "escolaridade", nullable = false)
    private Escolaridade escolaridade;

    @Enumerated (EnumType.STRING)
    @Getter @Setter
    @Column (name = "sexo", nullable = false)
    private Sexo sexo;

//    @Getter @Setter
//    @Column (name = "rua", nullable = false, length = 50)
//    @NotBlank (message = "Rua não pode ser nula")
//    private String rua;
//    @Getter @Setter
//    @Column(name = "bairro", nullable = false, length = 50)
//    @NotBlank (message = "Bairro não pode ser nulo")
//    private String bairro;
//    @Getter @Setter
//    @Column (name = "numero_casa", nullable = false)
//    private int numeroCasa;
//    @Getter @Setter
//    @Column (name = "cep")
//    @NotBlank (message = "CEP não pode ser nulo")
//    private String cep;
    @Getter @Setter
    @Column (name = "cadastro_por")
    private String cadastroPor;

    @Getter @Setter
    @Column (name = "cadastro_data")
    private LocalDateTime dataCadastro;
    @Getter @Setter
    @Column (name = "edicao_cadastro")
    private LocalDateTime edicaoCadastro;

    @Getter @Setter
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
