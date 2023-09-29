package projetointegrador.cciar.projetointegradorcciar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projetointegrador.cciar.projetointegradorcciar.entity.Administrador;

public interface AdministradorRepository extends JpaRepository<Administrador,Long> {
    Administrador findByCpf(String cpf);
}
