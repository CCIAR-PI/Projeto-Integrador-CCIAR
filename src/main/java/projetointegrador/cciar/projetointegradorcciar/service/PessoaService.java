package projetointegrador.cciar.projetointegradorcciar.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import projetointegrador.cciar.projetointegradorcciar.dto.PessoaDTO;
import projetointegrador.cciar.projetointegradorcciar.entity.Pessoa;
import projetointegrador.cciar.projetointegradorcciar.repository.PessoaRepository;

@Service
public class PessoaService {
    @Autowired
    private PessoaRepository pessoaRepository;
    @Transactional(rollbackFor = Exception.class)
    public void validaPessoa (PessoaDTO pessoaDTO)
    {
        var pessoa = new Pessoa();
        BeanUtils.copyProperties(pessoaDTO, pessoa);

        Pessoa pessoaExistente = pessoaRepository.findByCpf(pessoa.getCpf());
        Assert.isTrue(pessoaExistente == null || pessoaExistente.equals(pessoa.getCpf()), "CPF Já existente");

        Pessoa rgExistente = pessoaRepository.findByRg(pessoa.getRg());
        Assert.isTrue(rgExistente == null || rgExistente.equals(pessoa.getRg()), "RG Já existente");

        pessoa.setAtivo(true);
        this.pessoaRepository.save(pessoa);
    }

    @Transactional (rollbackFor = Exception.class)
    public void editarPessoa (final Long id, final PessoaDTO pessoaDTO){

        var pessoa = new Pessoa();
        BeanUtils.copyProperties(pessoaDTO, pessoa);

        final Pessoa pessoaBanco = this.pessoaRepository.findById(id).orElse(null);

        if (pessoaBanco == null || !pessoaBanco.getId().equals(id)){
            throw new RegistroNaoEncontradoException("Não foi possível identificar o registro informado");
        }
        this.pessoaRepository.save(pessoa);
    }

    @Transactional (rollbackFor = Exception.class)
    public void deletarPessoa (final Long id){
        final Pessoa pessoaBanco = this.pessoaRepository.findById(id).orElse(null);

        if (pessoaBanco == null || !pessoaBanco.getId().equals(id)){
            throw new RegistroNaoEncontradoException("Não foi possível identificar o registro informado");
        }

        this.pessoaRepository.delete(pessoaBanco);
    }

    public static class RegistroNaoEncontradoException extends RuntimeException {
        public RegistroNaoEncontradoException(String message) {
            super(message);
        }
    }

}
