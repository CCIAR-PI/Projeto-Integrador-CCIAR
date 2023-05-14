package cci.projetointegrador.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table (name = "agenda", schema = "public")
public class Agenda {

    @Id
    @Getter
    @Column (name = "id", nullable = false, unique = true)
    private Long id;

    @Getter
    @Setter
    @Column(name = "atividades", nullable = false)
    @OneToMany (mappedBy = "agenda", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List <Atividade> atividades;

    @Getter @Setter
    @Column (name = "dia_atual", nullable = false)
    private LocalDateTime diaAtual;

    @Getter @Setter
    @Column (name = "descricao_atividade", nullable = false)
    private String descricaoAtividade;
}
