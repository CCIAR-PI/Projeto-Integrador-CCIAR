package cci.projetointegrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Agenda extends JpaRepository <cci.projetointegrador.entity.Agenda,Long> {
}
