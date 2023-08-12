package projetointegrador.cciar.projetointegradorcciar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import projetointegrador.cciar.projetointegradorcciar.entity.Administrador;
import projetointegrador.cciar.projetointegradorcciar.repository.AdministradorRepository;

@Service
public class AdministradorService {
    @Autowired
    private AdministradorRepository administradorRepository;

    @Transactional(rollbackFor = Exception.class)
    public void validaAdm (Administrador administrador){

        Assert.isTrue(administrador.getLoginNome().length() <= 50 ,"Nome de login acima do limite de 50 caracteres");
        Assert.isTrue(!administrador.getLoginNome().equals(""), "Nome de login não pode ser nulo");

        Assert.isTrue(administrador.getSenha().length() <= 50, "Escolha uma senha abaixo de 50 caracteres");
        Assert.isTrue(!administrador.getSenha().equals(""), "Senha não pode ser nula");

        Assert.isTrue(administrador.getEmail().length() <= 60, "E-mail acima do limite de 60 caracteres");
        Assert.isTrue(!administrador.getEmail().equals(""), "E-mail não pode ser nulo");

        Assert.isTrue(!administrador.getTelefone().equals(""), "Telefone não pode ser nulo");

        Assert.isTrue(administrador.getEmailRecup().length() <= 60, "E-mail de recuperação acima do limite de 60 caracteres");


        Administrador administradorExistente = administradorRepository.findByCpf(administrador.getCpf());

        Assert.isTrue(administradorExistente == null || administradorExistente.equals(administrador.getCpf()), "CPF Já existente"); // ADICIONAR ESSAS MESMAS LINHAS NA CLASSE ADM SERVICE -- PEDRO


        administradorRepository.save(administrador);

    }
}
