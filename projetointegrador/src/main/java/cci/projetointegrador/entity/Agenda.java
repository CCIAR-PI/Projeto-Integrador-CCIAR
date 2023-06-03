package cci.projetointegrador.entity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table (name = "agenda", schema = "public")
public class Agenda {

    @Id
    @Getter
    @Column (name = "id", nullable = false, unique = true)
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    @Getter
    @Setter
    @Column(name = "atividades", nullable = false)
    @OneToMany (mappedBy = "agenda", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List <Atividade> atividades;

    @Getter @Setter
    @Column (name = "dia_atual", nullable = false)
    private LocalDateTime diaAtual;

    @PrePersist
    private void PrePersist (){
        this.diaAtual = LocalDateTime.now();
    }

}
