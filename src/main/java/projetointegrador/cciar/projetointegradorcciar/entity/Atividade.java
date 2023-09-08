package projetointegrador.cciar.projetointegradorcciar.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalTime;

@Entity
@Table(name = "atividades", schema = "public")
public class Atividade {
    @Getter
    @Id
    @Column(name = "atividade", nullable = false, unique = true)
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    @Getter @Setter
    @Column (name = "ativo", nullable = false)
    private boolean ativo;
    @Getter @Setter
    @Column (name = "nome_atividade", nullable = false)
    private String nomeAtividade;
    @Getter @Setter
    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Getter @Setter
    @Column (name = "nome_autor")
    private String nomeAutor;
    @Getter @Setter
    @Column (name = "horario_cadastro")
    private LocalTime horarioCadastro;

//    @Getter @Setter
//    @Column (name = "dia_semana")
//    private Dia dia;


    @PrePersist
    private void PrePersist () {
        this.horarioCadastro = LocalTime.now();
        this.ativo = true;
    }
}
