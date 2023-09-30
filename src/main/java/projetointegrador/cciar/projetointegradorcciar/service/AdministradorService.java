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

        Administrador administradorExistente = administradorRepository.findByCpf(administrador.getCpf());

        Assert.isTrue(administradorExistente == null || administradorExistente.equals(administrador.getCpf()), "CPF Já existente");

        administradorRepository.save(administrador);
    }


    @Transactional(rollbackFor = Exception.class)
    public void editaAdm (final Long id, AdministradorDTO administradorDTO){

        final Administrador administrador1 = this.administradorRepository.findById(id).orElse(null);

        if (administrador1 == null || !administrador1.getId().equals(id)) {
            throw new RegistroNaoEncontradoException("Nao foi possivel indentificar o registro informado");
        }

        BeanUtils.copyProperties(administradorDTO, administrador1);

        administradorRepository.save(administrador1);
    }

    @Transactional (rollbackFor = Exception.class)
    public void deletaAdm (final Long id){
        final Administrador admBanco = this.administradorRepository.findById(id).orElse(null);

        if (admBanco == null || !admBanco.getId().equals(id)){
            throw new AdministradorService.RegistroNaoEncontradoException("Não foi possível identificar o registro informado");
        }

        this.administradorRepository.delete(admBanco);
    }

    public static class RegistroNaoEncontradoException extends RuntimeException {
        public RegistroNaoEncontradoException(String message) {
            super(message);
        }
    }
}
