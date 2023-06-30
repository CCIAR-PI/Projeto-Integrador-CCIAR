package cci.projetointegrador.repository;

import cci.projetointegrador.entity.Administrador;
import cci.projetointegrador.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministradorRepository extends JpaRepository <Administrador,Long> {
    Administrador findByCpf(String cpf);
}
