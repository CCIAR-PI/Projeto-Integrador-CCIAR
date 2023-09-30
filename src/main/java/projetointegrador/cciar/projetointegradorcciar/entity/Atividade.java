package projetointegrador.cciar.projetointegradorcciar.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "atividades", schema = "public")
@Getter @Setter
public class Atividade {

    public Atividade() {
    }

    public Atividade(Long id, boolean ativo, String nomeAtividade, String descricao, LocalTime horarioCadastro, LocalDateTime dataAtividade) {
        this.id = id;
        this.ativo = ativo;
        this.nomeAtividade = nomeAtividade;
        this.descricao = descricao;
        this.horarioCadastro = horarioCadastro;
        this.dataAtividade = dataAtividade;
    }

    @Id
    @Column(name = "atividade", nullable = false, unique = true)
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    @Column (name = "ativo", nullable = false)
    private boolean ativo;
    @Column (name = "nome_atividade", nullable = false)
    private String nomeAtividade;
    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column (name = "horario_cadastro")
    private LocalTime horarioCadastro;

    @Column (name = "data_atividade")
    private LocalDateTime dataAtividade;

    @PrePersist
    private void prePersist () {
        this.horarioCadastro = LocalTime.now();
        this.ativo = true;
    }
}
