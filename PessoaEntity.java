package cci.projetointegrador.entity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table (name = "Pessoas", schema = "public")
public class PessoaEntity {
    @Id
    @Getter
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    protected long id;

    @Getter @Setter
    @Column (name = "nome", nullable = false)
    private String nome;
    @Getter @Setter
    @Column (name = "cpf", nullable = false, unique = true)
    private String cpf;
    @Getter @Setter
    @Column (name = "dataNascimento", nullable = false)
    private String dataNascimento;
    @Getter @Setter
    @Column (name = "rg", nullable = false, unique = true)
    private String rg;
    @Getter @Setter
    @Column (name = "hash_imagem", nullable = false, unique = true)
    private String hashImg;
    @Getter @Setter
    @Column (name = "telefone_emergencia", nullable = false)
    private String telefoneEmergencia;
    @Getter @Setter
    @Column (name = "naturalidade", nullable = false)
    private String naturalidade;
    @Getter @Setter
    @Column (name = "rua", nullable = false)
    private String rua;
    @Getter @Setter
    @Column (name = "bairro", nullable = false)
    private String bairro;
    @Getter @Setter
    @Column (name = "numero_casa", nullable = false)
    private int numeroCasa;
    @Getter @Setter
    @Column (name = "cep", nullable = false)
    private String cep;
    @Getter @Setter
    @Column (name = "cadastro_por", nullable = false)
    private LocalDate cadastroPor;
    @Getter @Setter
    @Column (name = "edicao_cadastro", nullable = false)
    private LocalDate edicaocadastro;

    @Getter @Setter
    @Column (name = "ativo", nullable = false)
    private boolean ativo;

}

