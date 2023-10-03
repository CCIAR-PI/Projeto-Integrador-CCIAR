package projetointegrador.cciar.projetointegradorcciar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projetointegrador.cciar.projetointegradorcciar.entity.Pessoa;

import java.util.List;

public interface PessoaRepository extends JpaRepository<Pessoa,Long> {
    Pessoa findByCpf(String cpf);

    List <Pessoa> findByNome (String nome);
    Pessoa findByRg (String rg);

}
