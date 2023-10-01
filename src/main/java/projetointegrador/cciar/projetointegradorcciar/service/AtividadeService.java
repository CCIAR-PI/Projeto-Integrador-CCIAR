package projetointegrador.cciar.projetointegradorcciar.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projetointegrador.cciar.projetointegradorcciar.dto.AtividadeDTO;
import projetointegrador.cciar.projetointegradorcciar.entity.Atividade;
import projetointegrador.cciar.projetointegradorcciar.repository.AtividadeRepository;

import java.time.LocalDateTime;

@Service
public class AtividadeService {
    @Autowired
    private AtividadeRepository atividadeRepository;

    @Transactional(rollbackFor = Exception.class)
    public void validaAtividade (AtividadeDTO atividadeDTO){

        var atividade = new Atividade();
        BeanUtils.copyProperties(atividadeDTO, atividade);

        atividade.setDataAtividade(LocalDateTime.now());
        this.atividadeRepository.save(atividade);
    }

    @Transactional (rollbackFor = Exception.class)
    public void editarAtividade (final Long id, final AtividadeDTO atividadeDTO) {

        final Atividade atividadeBanco = this.atividadeRepository.findById(id).orElse(null);

        if (atividadeBanco == null || !atividadeBanco.getId().equals(id)) {
            throw new RegistroNaoEncontradoException("Não foi possível identificar o registro informado");
        }

        BeanUtils.copyProperties(atividadeDTO, atividadeBanco);

        this.atividadeRepository.save(atividadeBanco);
    }

    @Transactional (rollbackFor = Exception.class)
    public void deletarAtividade (final Long id){
        final Atividade atividadeBanco = this.atividadeRepository.findById(id).orElse(null);

        if (atividadeBanco == null || !atividadeBanco.getId().equals(id)){
            throw new RegistroNaoEncontradoException("Não foi possível identificar o registro informado");
        }

        this.atividadeRepository.delete(atividadeBanco);
    }

    public static class RegistroNaoEncontradoException extends RuntimeException {
        public RegistroNaoEncontradoException(String message) {
            super(message);
        }
    }
}
