package projetointegrador.cciar.projetointegradorcciar.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.web.bind.annotation.RequestMapping;

@Entity
@Table(name = "administrador", schema = "public")
@RequestMapping("/api/administrador")
public class Administrador {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO) // Vai gerar um valor aleatório.
    @Column (name = "id", nullable = false, unique = true)
    private Long id;

    @Getter @Setter
    @Column (name = "login_nome", nullable = false)
    private String  loginNome;

    @Getter @Setter
    @Pattern(regexp = "\\(?\\d{2,}\\)?[ -]?\\d{4,}[\\-\\s]?\\d{4}", message = "Formato de telefone inválido")
    @Column (name = "telefone", nullable = false)
    private String telefone;
    @Getter @Setter
    @Column (name = "senha", nullable = false)
    private String senha;
    @Getter @Setter
    @Column (name = "email", nullable = false)
    private String email;

    @Getter @Setter
    @Column (name = "email_recup", nullable = false)
    private String emailRecup;

    @Getter @Setter
    @Column(name = "cpf_adm", nullable = false)
    @CPF(message = "CPF inválido")
    private String cpf;
}
