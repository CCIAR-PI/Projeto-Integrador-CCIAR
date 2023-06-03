package cci.projetointegrador.entity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity // Indica que a classe é uma tabela.
@Table (name = "Pessoas", schema = "public")

public class Pessoa {
    @Id
    @Getter
    @GeneratedValue (strategy = GenerationType.AUTO) // Gera um valor aleatório
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    @Getter @Setter
    @Column (name = "nome", nullable = false, length = 50)
    private String nome;
    @Getter @Setter
    @Column (name = "cpf", nullable = false, unique = true, length = 14)
    private String cpf;
    @Getter @Setter
    @Column (name = "data_nascimento", nullable = false)
    private int dataNascimento;
    @Getter @Setter
    @Column (name = "rg", nullable = false, unique = true, length = 12)
    private String rg;
//    @Getter @Setter
//    @Column (name = "hash_imagem", nullable = false, unique = true)
//    private String hashImg;

    @Getter @Setter
    @Column (name = "telefone_emergencia", nullable = false, length = 17)
    private String telefoneEmergencia;
    @Getter @Setter
    @Column (name = "naturalidade", nullable = false, length = 50)
    private String naturalidade;

    @Getter @Setter
    @Column (name = "nacionalidade", nullable = false, length = 50)
    private String nacionalidade;

    @Enumerated (EnumType.STRING)
    @Getter @Setter
    @Column (name = "escolaridade", nullable = false)
    private Escolaridade escolaridade;

    @Enumerated (EnumType.STRING)
    @Getter @Setter
    @Column (name = "sexo", nullable = false)
    private Sexo sexo;

    @Getter @Setter
    @Column (name = "rua", nullable = false, length = 50)
    private String rua;
    @Getter @Setter
    @Column (name = "bairro", nullable = false, length = 50)
    private String bairro;
    @Getter @Setter
    @Column (name = "numero_casa", nullable = false)
    private int numeroCasa;
    @Getter @Setter
    @Column (name = "cep", nullable = false, length = 9)
    private String cep;
    @Getter @Setter
    @Column (name = "cadastro_por", nullable = false)
    private String cadastroPor;

    @Getter @Setter
    @Column (name = "cadastro_data", nullable = false)
    private LocalDateTime dataCadastro;
    @Getter @Setter
    @Column (name = "edicao_cadastro")
    private LocalDateTime edicaoCadastro;

    @Getter @Setter
    @Column (name = "ativo", nullable = false)
    private boolean ativo;

//    @ManyToOne
//    @JoinColumn (name = "atividadeid")
//    private Atividade atividade;

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

