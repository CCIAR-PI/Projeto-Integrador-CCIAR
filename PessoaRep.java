package cci.projetointegrador.repository;

import cci.projetointegrador.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //representa que essa classe interface é um repositório extendido de JpaRepository
public interface PessoaRepository extends JpaRepository <Pessoa,Long> {
}
