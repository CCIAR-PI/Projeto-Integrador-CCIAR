package cci.projetointegrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Administrador extends JpaRepository <cci.projetointegrador.entity.Administrador,Long> {
}
