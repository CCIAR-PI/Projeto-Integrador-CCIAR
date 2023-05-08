package cci.projetointegrador.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table (name = "atividades", schema = "public")
public class Atividade {

    @Getter
    @Id
    @Column(name = "atividade", nullable = false, unique = true)
    @GeneratedValue (strategy = GenerationType.AUTO)
    protected Long id;

    @Getter @Setter
    @Column (name = "titulo_atividade", nullable = false)
    private String tituloAtividade;
    @Getter @Setter
    @Column (name = "descricao", nullable = false)
    private String descricao;
    @Getter @Setter
    @Column (name = "nome_autor", nullable = false)
    private String nomeAutor;

    @Getter @Setter
    @Column (name = "horario_cadastro", nullable = false)
    private LocalDateTime horarioCadastro;

    @ManyToOne
    @JoinColumn (name = "agendaid")
    private Agenda agenda;

    @Getter
    @Setter
    @Column(name = "pessoa", nullable = false)
    @OneToMany (mappedBy= "atividade", fetch = FetchType.LAZY, cascade = CascadeType.ALL) // Fetch = Carrega só uma vez  e deixa a lista carregada no banco
    // Cascade = executa tudo de uma vez
    private List<Pessoa> pessoas;
}
