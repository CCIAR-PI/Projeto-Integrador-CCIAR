package cci.projetointegrador.repository;

import cci.projetointegrador.entity.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministradorRepository extends JpaRepository <Administrador,Long> {
}
