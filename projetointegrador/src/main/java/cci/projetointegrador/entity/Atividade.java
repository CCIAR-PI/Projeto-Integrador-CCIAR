package cci.projetointegrador.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table (name = "atividades", schema = "public")
@RequestMapping("/api/atividade")
public class Atividade {

    @Getter
    @Id
    @Column(name = "atividade", nullable = false, unique = true)
    @GeneratedValue (strategy = GenerationType.AUTO)
    protected Long id;

    @Getter @Setter
    @Column (name = "nome_atividade", nullable = false)
    private String nomeAtividade;
    @Getter @Setter
    @Column (name = "descricao", nullable = false)
    private String descricao;
    @Getter @Setter
    @Column (name = "nome_autor")
    private String nomeAutor;

    @Getter @Setter
    @Column (name = "horario_cadastro")
    private LocalDateTime horarioCadastro;

    @ManyToOne
    @JoinColumn (name = "agenda_id")
    private Agenda agenda;

    @PrePersist
    private void PrePersist () {
        this.horarioCadastro = LocalDateTime.now();
    }

//    @Getter
//    @Setter
//    @Column(name = "pessoa")
//    @OneToMany (mappedBy= "atividade", fetch = FetchType.LAZY, cascade = CascadeType.ALL) // Fetch = Carrega só uma vez  e deixa a lista carregada no banco
//    // Cascade = executa tudo de uma vez
//    private List<Pessoa> pessoas;
}




