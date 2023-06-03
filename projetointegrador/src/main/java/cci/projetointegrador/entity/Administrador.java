package cci.projetointegrador.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table (name = "administrador", schema = "public")
public class Administrador {
    @Id
    @Getter
    @GeneratedValue (strategy = GenerationType.AUTO) // Vai gerar um valor aleatório.
    @Column (name = "id", nullable = false, unique = true)
    private Long id;

    @Getter @Setter
    @Column (name = "login_nome", nullable = false)
    private String  loginNome;

    @Getter @Setter
    @Column (name = "telefone", nullable = false)
    private String telefone;  // Fazer validação do telefone <-- @Pattern(regexp = "\\(?\\d{2,}\\)?[ -]?\\d{4,}[\\-\\s]?\\d{4}", message = "Formato de telefone inválido") -- Joao
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
    @Column (name = "cpf_adm", nullable = false)
    private String cpf;



}
