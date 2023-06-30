package cci.projetointegrador.repository;

import cci.projetointegrador.entity.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendaRepository extends JpaRepository <Agenda,Long> {
}
