package cci.projetointegrador.repository;

import cci.projetointegrador.entity.Atividade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //repositorio da atividade
public interface AtividadeRepository extends JpaRepository <Atividade,Long> {
}
