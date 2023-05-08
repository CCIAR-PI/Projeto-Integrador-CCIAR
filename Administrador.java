package cci.projetointegrador.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity // Entitiy - Definir que a classe é uma tabela.
// Table criar uma tabela com nome de administrado. Schema - Maneira como vai ser armazenado.
@Table (name = "administrador", schema = "public")
// Vai criar a calsse administrador.
public class Administrador {
    @Id // Id - Vai identificar que esse atributo é um id.
    @Getter // Getter - Pegar valor do atributo.
    @GeneratedValue (strategy = GenerationType.AUTO) // Vai gerar um valor aleatório.
    @Column (name = "id", nullable = false, unique = true) // Cria uma coluna com nome id
    // essa coluna não pode ser null e ela é unica.
    protected Long id;

    // Criando atributos que estão dentro da classe Administrador.

    @Getter @Setter
    @Column (name = "login_nome", nullable = false, unique = true)
    private String  loginNome;
    @Getter @Setter
    @Column (name = "telefone", nullable = false)
    private String telefone;
    @Getter @Setter
    @Column (name = "senha", nullable = false)
    private String senha;
    @Getter @Setter
    @Column (name = "email", nullable = false)
    private String email;

}
