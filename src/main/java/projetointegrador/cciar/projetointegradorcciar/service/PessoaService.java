package projetointegrador.cciar.projetointegradorcciar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import projetointegrador.cciar.projetointegradorcciar.entity.Pessoa;
import projetointegrador.cciar.projetointegradorcciar.repository.PessoaRepository;

@Service
public class PessoaService {
    @Autowired
    private PessoaRepository pessoaRepository;
    @Transactional(rollbackFor = Exception.class)
    public void validaPessoa (Pessoa pessoa)
    {
        Assert.isTrue(!pessoa.getNome().equals(""), "Nome não pode ser nulo");
        Assert.isTrue(pessoa.getNome().length() <= 50, "Nome acima do limite de caracteres");

        Assert.isTrue(!pessoa.getCpf().equals(""),"CPF não pode ser nulo");


        Assert.isTrue(pessoa.getDataNascimento() >= 0, "Data de nascimento não pode ser nula");

        Assert.isTrue(!pessoa.getRg().equals("") , "Rg não pode ser nulo");
        Assert.isTrue(pessoa.getRg().length() <= 9, "Rg inválido");

        Assert.isTrue(!pessoa.getTelefone().equals(""), "Telefone não pode ser nulo");



        Assert.isTrue(!pessoa.getNaturalidade().equals(""), "Naturalidade não pode ser nula");
        Assert.isTrue(pessoa.getNaturalidade().length() <= 50, "Naturalidade acima do limite de caracteres");

        Assert.isTrue(!pessoa.getNacionalidade().equals(""), "Nacionalidade não pode ser nula");
        Assert.isTrue(pessoa.getNacionalidade().length() <= 50, "Nacionalidade acima do limite de caracteres");

        Assert.isTrue(!pessoa.getRua().equals(""), "RUA não pode ser nulo");
        Assert.isTrue(pessoa.getRua().length() <= 50, "Rua acima do limite de caracteres");

        Assert.isTrue(!pessoa.getBairro().equals(""), "Bairro não pode ser nulo");
        Assert.isTrue(pessoa.getBairro().length() <= 50, "Bairro acima do limite de caracteres");

        Assert.isTrue(!pessoa.getNaturalidade().equals(""), "Campo Naturalidade não pode ser nula");
        Assert.isTrue(pessoa.getNaturalidade().length() <= 50, "Campo Naturalidade acima do limite de caracteres");


        Assert.isTrue(!pessoa.getRua().equals(""), "Campo RUA não pode ser nulo");
        Assert.isTrue(pessoa.getRua().length() <= 50, "Campo RUA acima do limite de caracteres");

        Assert.isTrue(!pessoa.getBairro().equals(""), "Campo Bairro não pode ser nulo");
        Assert.isTrue(pessoa.getBairro().length() <= 50, "Campo Bairro acima do limite de caracteres");



        Assert.isTrue(pessoa.getNumeroCasa() >= 0, "Número da casa não pode ser nulo");

        Assert.isTrue(!pessoa.getCep().equals(""), "CEP não pode ser nulo");
        Assert.isTrue(pessoa.getCep().length() <= 8 , "CEP inválido");

        pessoa.setAtivo(true);

        Pessoa PessoaExistente = pessoaRepository.findByCpf(pessoa.getCpf());

        Assert.isTrue(PessoaExistente == null || PessoaExistente.equals(pessoa.getCpf()), "CPF Já existente");


        Pessoa rgExistente = pessoaRepository.findByRg(pessoa.getRg());
        Assert.isTrue(rgExistente == null || rgExistente.equals(pessoa.getRg()), "RG Já existente");



        this.pessoaRepository.save(pessoa);

    }

    @Transactional (rollbackFor = Exception.class)
    public void editarPessoa (final Long id, final Pessoa pessoa){

        final Pessoa pessoaBanco = this.pessoaRepository.findById(id).orElse(null);

        if (pessoaBanco == null || !pessoaBanco.getId().equals(id)){
            throw new RuntimeException("Não foi possível identificar o registro informado");
        }

        Assert.isTrue(!pessoa.getNome().equals(""), "Nome não pode ser nulo");
        Assert.isTrue(pessoa.getNome().length() <= 50, "Nome acima do limite de caracteres");

        Assert.isTrue(!pessoa.getCpf().equals(""),"CPF não pode ser nulo");


        Assert.isTrue(pessoa.getDataNascimento() >= 0, "Data de nascimento não pode ser nula");

        Assert.isTrue(!pessoa.getRg().equals("") , "Rg não pode ser nulo");
        Assert.isTrue(pessoa.getRg().length() <= 9, "Rg inválido");

        Assert.isTrue(!pessoa.getTelefone().equals(""), "Telefone não pode ser nulo");



        Assert.isTrue(!pessoa.getNaturalidade().equals(""), "Naturalidade não pode ser nula");
        Assert.isTrue(pessoa.getNaturalidade().length() <= 50, "Naturalidade acima do limite de caracteres");

        Assert.isTrue(!pessoa.getNacionalidade().equals(""), "Nacionalidade não pode ser nula");
        Assert.isTrue(pessoa.getNacionalidade().length() <= 50, "Nacionalidade acima do limite de caracteres");

        Assert.isTrue(!pessoa.getRua().equals(""), "RUA não pode ser nulo");
        Assert.isTrue(pessoa.getRua().length() <= 50, "Rua acima do limite de caracteres");

        Assert.isTrue(!pessoa.getBairro().equals(""), "Bairro não pode ser nulo");
        Assert.isTrue(pessoa.getBairro().length() <= 50, "Bairro acima do limite de caracteres");

        Assert.isTrue(!pessoa.getNaturalidade().equals(""), "Campo Naturalidade não pode ser nula");
        Assert.isTrue(pessoa.getNaturalidade().length() <= 50, "Campo Naturalidade acima do limite de caracteres");


        Assert.isTrue(!pessoa.getRua().equals(""), "Campo RUA não pode ser nulo");
        Assert.isTrue(pessoa.getRua().length() <= 50, "Campo RUA acima do limite de caracteres");

        Assert.isTrue(!pessoa.getBairro().equals(""), "Campo Bairro não pode ser nulo");
        Assert.isTrue(pessoa.getBairro().length() <= 50, "Campo Bairro acima do limite de caracteres");



        Assert.isTrue(pessoa.getNumeroCasa() >= 0, "Número da casa não pode ser nulo");

        Assert.isTrue(!pessoa.getCep().equals(""), "CEP não pode ser nulo");

        this.pessoaRepository.save(pessoa);
    }

    @Transactional (rollbackFor = Exception.class)
    public void deletarPessoa (final Long id){
        final Pessoa pessoaBanco = this.pessoaRepository.findById(id).orElse(null);

        if (pessoaBanco == null || !pessoaBanco.getId().equals(id)){
            throw new RuntimeException("Não foi possível identificar o registro informado");
        }

        this.pessoaRepository.delete(pessoaBanco);
    }
}
