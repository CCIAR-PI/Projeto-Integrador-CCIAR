package projetointegrador.cciar.projetointegradorcciar.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDateTime;

@Entity
@Table(name = "Pessoas", schema = "public")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Pessoa {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    @Column (name = "nome", nullable = false, length = 50)
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ\\s]*$", message = "Nome não pode conter caracteres especiais")
    private String nome;
    @Column (name = "cpf", nullable = false, unique = true, length = 14)
    @CPF(message = "CPF inválido")
    private String cpf;

    @OneToOne
    @JoinColumn(name = "endereco_id")
    @JsonManagedReference
    private Endereco endereco;
    @Column (name = "data_nascimento", nullable = false)
    private int dataNascimento;
    @Column (name = "rg", nullable = false, unique = true, length = 12)
    private String rg;

    @Column (name = "telefone", nullable = false, length = 17)
    @Pattern(regexp = "\\(?\\d{2,}\\)?[ -]?\\d{4,}[\\-\\s]?\\d{4}", message = "Formato de telefone inválido")
    private String telefone;
    @Column (name = "naturalidade", nullable = false, length = 50)
    private String naturalidade;

    @Column (name = "nacionalidade", nullable = false, length = 50)
    private String nacionalidade;

    @Enumerated (EnumType.STRING)
    @Column (name = "escolaridade", nullable = false)
    private Escolaridade escolaridade;

    @Enumerated (EnumType.STRING)
    @Column (name = "sexo", nullable = false)
    private Sexo sexo;

    @OneToOne
    @JoinColumn(name = "adm_id")
    @JsonManagedReference
    private Administrador cadastroPor;

    @Column (name = "cadastro_data")
    private LocalDateTime dataCadastro;
    @Column (name = "edicao_cadastro")
    private LocalDateTime edicaoCadastro;

    @Column (name = "ativo", nullable = false)
    private boolean ativo;
}

