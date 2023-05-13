package cci.projetointegrador.service;

import cci.projetointegrador.configs.ValidaCpf;
import cci.projetointegrador.entity.Pessoa;
import cci.projetointegrador.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private ValidaCpf validaCpf;
    @Transactional (rollbackFor = Exception.class)
    public void validaPessoa (Pessoa pessoa)
    {
        Assert.isTrue(pessoa.getNome().length() <= 50, "Nome acima do limite de caracteres");
        Assert.isTrue(pessoa.getNome().equals(""), "Nome não pode ser nulo");

        Assert.isTrue(pessoa.getCpf().equals(""),"CPF não pode ser nulo");

        if (this.validaCpf.isCPF(pessoa.getCpf()) == false) {
            throw new RuntimeException("CPF inválido");
        }

        Assert.isTrue(pessoa.getDataNascimento() < 0, "Data de nascimento não pode ser nula");

        Assert.isTrue(pessoa.getRg().equals("") , "Rg não pode ser nulo");
        Assert.isTrue(pessoa.getRg().length() <= 12, "Rg inválido");

        Assert.isTrue(pessoa.getTelefoneEmergencia().equals(""), "Telefone não pode ser nulo");
        Assert.isTrue(pessoa.getTelefoneEmergencia().length() <= 17, "Telefone inválido");

        Assert.isTrue(pessoa.getNaturalidade().equals(""), "Naturalidade não pode ser nula");
        Assert.isTrue(pessoa.getNaturalidade().length() > 50, "Naturalidade acima do limite de caracteres");

        Assert.isTrue(pessoa.getNacionalidade().equals(""), "Nacionalidade não pode ser nula");
        Assert.isTrue(pessoa.getNacionalidade().length() > 50, "Nacionalidade acima do limite de caracteres");

        Assert.isTrue(pessoa.getRua().equals(""), "RUA não pode ser nulo");
        Assert.isTrue(pessoa.getRua().length() > 50, "Rua acima do limite de caracteres");

        Assert.isTrue(pessoa.getBairro().equals(""), "Bairro não pode ser nulo");
        Assert.isTrue(pessoa.getBairro().length() > 50, "Bairro acima do limite de caracteres");


        Assert.isTrue(pessoa.getNumeroCasa() <= 0, "Número da casa não pode ser nulo");

        Assert.isTrue(pessoa.getCep().equals(""), "CEP não pode ser nulo");
        Assert.isTrue(pessoa.getRua().length() <= 9 , "CEP inválido");

        this.pessoaRepository.save(pessoa);

    }


}
