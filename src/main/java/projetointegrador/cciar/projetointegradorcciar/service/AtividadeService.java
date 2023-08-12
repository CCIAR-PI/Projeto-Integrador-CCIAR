package projetointegrador.cciar.projetointegradorcciar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import projetointegrador.cciar.projetointegradorcciar.entity.Atividade;
import projetointegrador.cciar.projetointegradorcciar.repository.AtividadeRepository;

import java.time.format.DateTimeFormatter;

@Service
public class AtividadeService {
    @Autowired
    private AtividadeRepository atividadeRepository;

    @Transactional(rollbackFor = Exception.class)
    public void validaAtividade (Atividade atividade){
        Assert.isTrue(!atividade.getNomeAtividade().equals(""), "Nome da atividade não pode ser nulo");
        Assert.isTrue(atividade.getNomeAtividade().length()  <= 100  , "Limite de caracteres excedido");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String horarioFormatado = atividade.getHorarioCadastro().format(formatter);

        this.atividadeRepository.save(atividade);
    }

    @Transactional (rollbackFor = Exception.class)
    public void editarAtividade (final Long id, final Atividade atividade) {

        final Atividade atividadeBanco = this.atividadeRepository.findById(id).orElse(null);

        if (atividadeBanco == null || !atividadeBanco.getId().equals(id)) {
            throw new RuntimeException("Não foi possível identificar o registro informado");
        }

        this.atividadeRepository.save(atividade);
    }

    @Transactional (rollbackFor = Exception.class)
    public void deletarAtividade (final Long id){
        final Atividade atividadeBanco = this.atividadeRepository.findById(id).orElse(null);

        if (atividadeBanco == null || !atividadeBanco.getId().equals(id)){
            throw new RuntimeException("Não foi possível identificar o registro informado");
        }

        this.atividadeRepository.delete(atividadeBanco);
    }

}
