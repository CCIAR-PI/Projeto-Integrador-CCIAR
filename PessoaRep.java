package cci.projetointegrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRep extends JpaRepository <PessoaRep,Long> {
    Object getId();
}
