package projetointegrador.cciar.projetointegradorcciar.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name = "administrador", schema = "public")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Administrador {

    @OneToOne(mappedBy = "cadastroPor") // Nome do campo em Pessoa
    @JsonBackReference
    private Pessoa pessoa;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "id", nullable = false, unique = true)
    private Long id;

    @Column (name = "nome_adm", nullable = false)
    private String  nomeAdm;

    @Column (name = "login_nome", nullable = false)
    private String  loginNome;

    @Pattern(regexp = "\\(?\\d{2,}\\)?[ -]?\\d{4,}[\\-\\s]?\\d{4}", message = "Formato de telefone inválido")
    @Column (name = "telefone", nullable = false)
    private String telefone;

    @Column (name = "senha", nullable = false)
    private String senha;

    @Column (name = "email", nullable = false)
    private String email;

    @Column (name = "email_recup", nullable = false)
    private String emailRecup;

    @Column(name = "cpf_adm", nullable = false)
    @CPF(message = "CPF inválido")
    private String cpf;
}
