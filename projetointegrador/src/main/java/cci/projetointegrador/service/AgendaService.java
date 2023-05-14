package cci.projetointegrador.service;

import cci.projetointegrador.entity.Agenda;
import cci.projetointegrador.repository.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class AgendaService {

    @Autowired
    private AgendaRepository agendaRepository;
    @Transactional(rollbackFor = Exception.class)
    public void validaAgenda (Agenda agenda) {
        Assert.isTrue(agenda.getDescricaoAtividade().length() > 100, "Limite de caracteres excedido");

        this.agendaRepository.save(agenda);
    }

}
