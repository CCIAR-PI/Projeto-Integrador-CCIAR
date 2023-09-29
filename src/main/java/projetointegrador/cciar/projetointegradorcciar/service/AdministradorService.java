package projetointegrador.cciar.projetointegradorcciar.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import projetointegrador.cciar.projetointegradorcciar.dto.AdministradorDTO;
import projetointegrador.cciar.projetointegradorcciar.entity.Administrador;
import projetointegrador.cciar.projetointegradorcciar.repository.AdministradorRepository;

@Service
public class AdministradorService {
    @Autowired
    private AdministradorRepository administradorRepository;

    @Transactional(rollbackFor = Exception.class)
    public void validaAdm (AdministradorDTO administradorDTO){

        var administrador = new Administrador();
        BeanUtils.copyProperties(administradorDTO, administrador);

        validacoes(administradorDTO);
        administradorRepository.save(administrador);
    }


    @Transactional(rollbackFor = Exception.class)
    public void editaAdm (final Long id, AdministradorDTO administradorDTO){

        var administrador = new Administrador();
        BeanUtils.copyProperties(administradorDTO, administrador);

        final Administrador administrador1 = this.administradorRepository.findById(id).orElse(null);

        if (administrador1 == null || administrador1.getId().equals(administradorDTO.getId())) {
            throw new RegistroNaoEncontradoException("Nao foi possivel indentificar o registro informado");
        }

        validacoes(administradorDTO);

        administradorRepository.save(administrador);

    }

    public static class RegistroNaoEncontradoException extends RuntimeException {
        public RegistroNaoEncontradoException(String message) {
            super(message);
        }
    }

    public void validacoes (AdministradorDTO administradorDTO){
        var administrador = new Administrador();
        BeanUtils.copyProperties(administradorDTO, administrador);

        Assert.isTrue(administrador.getLoginNome().length() <= 50 ,"Nome de login acima do limite de 50 caracteres");
        Assert.isTrue(!administrador.getLoginNome().equals(""), "Nome de login não pode ser nulo");

        Assert.isTrue(administrador.getSenha().length() <= 50, "Escolha uma senha abaixo de 50 caracteres");
        Assert.isTrue(!administrador.getSenha().equals(""), "Senha não pode ser nula");

        Assert.isTrue(administrador.getEmail().length() <= 60, "E-mail acima do limite de 60 caracteres");
        Assert.isTrue(!administrador.getEmail().equals(""), "E-mail não pode ser nulo");

        Assert.isTrue(!administrador.getTelefone().equals(""), "Telefone não pode ser nulo");

        Assert.isTrue(administrador.getEmailRecup().length() <= 60, "E-mail de recuperação acima do limite de 60 caracteres");


        Administrador administradorExistente = administradorRepository.findByCpf(administrador.getCpf());

        Assert.isTrue(administradorExistente == null || administradorExistente.equals(administrador.getCpf()), "CPF Já existente");

    }
}
