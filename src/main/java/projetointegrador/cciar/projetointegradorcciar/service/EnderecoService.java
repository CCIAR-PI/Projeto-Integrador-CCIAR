package projetointegrador.cciar.projetointegradorcciar.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import projetointegrador.cciar.projetointegradorcciar.dto.EnderecoDTO;
import projetointegrador.cciar.projetointegradorcciar.entity.Endereco;
import projetointegrador.cciar.projetointegradorcciar.repository.EnderecoRepository;

@Service
public class EnderecoService {

    @Autowired
    EnderecoRepository enderecoRepository;

    @Transactional(rollbackFor = Exception.class)
    public void validaEndereco (Long id, EnderecoDTO enderecoDTO){

        var endereco = new Endereco();
        BeanUtils.copyProperties(enderecoDTO, endereco);

        validacoes(enderecoDTO);
        this.enderecoRepository.save(endereco);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deletarEndereco(Long id) {

        final Endereco enderecoBanco = this.enderecoRepository.findById(id).orElse(null);

        if (enderecoBanco == null || !enderecoBanco.getId().equals(id)){
            throw new RegistroNaoEncontradoException("Não foi possível identificar o registro informado");
        }
        this.enderecoRepository.delete(enderecoBanco);
    }


    public void validacoes (EnderecoDTO enderecoDTO){
        var endereco = new Endereco();
        BeanUtils.copyProperties(enderecoDTO, endereco);

        Assert.isTrue(!endereco.getCep().equals(""), "CEP não pode ser nulo");
        Assert.isTrue(endereco.getCep().length()  <= 20  , "Limite de caracteres excedido");
    }

    public static class RegistroNaoEncontradoException extends RuntimeException {
        public RegistroNaoEncontradoException(String message) {
            super(message);
        }
    }


}
