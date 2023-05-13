package cci.projetointegrador.service;

import cci.projetointegrador.entity.Atividade;
import cci.projetointegrador.repository.AtividadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class AtividadeService {

    @Autowired
    private AtividadeRepository atividadeRepository;

    @Transactional (rollbackFor = Exception.class)
    public void validaAtividade (Atividade atividade){
        Assert.isTrue(atividade.getNomeAtividade().equals(""), "Nome da atividade não pode ser nulo");
        Assert.isTrue(atividade.getNomeAtividade().length()  > 100  , "Limite de caracteres excedido");


        this.atividadeRepository.save(atividade);
    }
}
