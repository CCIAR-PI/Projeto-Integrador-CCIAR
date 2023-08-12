package projetointegrador.cciar.projetointegradorcciar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projetointegrador.cciar.projetointegradorcciar.entity.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa,Long> {
    Pessoa findByCpf(String cpf);

    Pessoa findByRg (String rg);
}
