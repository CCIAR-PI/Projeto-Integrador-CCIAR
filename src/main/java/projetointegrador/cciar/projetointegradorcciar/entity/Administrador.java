package projetointegrador.cciar.projetointegradorcciar.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name = "administrador", schema = "public")
@Getter @Setter
public class Administrador {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Vai gerar um valor aleatório.
    @Column (name = "id", nullable = false, unique = true)
    private Long id;

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
