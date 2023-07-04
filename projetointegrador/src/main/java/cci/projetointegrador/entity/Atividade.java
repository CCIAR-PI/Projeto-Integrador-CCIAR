package cci.projetointegrador.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    @Column (name = "ativo", nullable = false)
    private boolean ativo;
    @Getter @Setter
    @Column (name = "nome_atividade", nullable = false)
    private String nomeAtividade;
    @Getter @Setter
    @Column (name = "descricao", nullable = false)
    private String descricao;

//    @Getter @Setter
//    @Column (name = "nome_autor")
//    private String nomeAutor;
    @Getter @Setter
    @Column (name = "horario_cadastro")
    private LocalTime horarioCadastro;

    @Getter @Setter
    @Column (name = "dia_semana")
    private Dia dia;


    @PrePersist
    private void PrePersist () {
        this.horarioCadastro = LocalTime.now();
        this.ativo = true;
    }

//    @Getter
//    @Setter
//    @Column(name = "pessoa")
//    @OneToMany (mappedBy= "atividade", fetch = FetchType.LAZY, cascade = CascadeType.ALL) // Fetch = Carrega só uma vez  e deixa a lista carregada no banco
//    // Cascade = executa tudo de uma vez
//    private List<Pessoa> pessoas;
}




