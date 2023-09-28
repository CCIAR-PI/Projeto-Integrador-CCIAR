package projetointegrador.cciar.projetointegradorcciar.unitTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import projetointegrador.cciar.projetointegradorcciar.dto.AtividadeDTO;

@SpringBootTest
class AtividadeTests {
    AtividadeDTO atividadeDTO = new AtividadeDTO();

    @Test
    void testeUmAtividade (){
        atividadeDTO.setNomeAtividade("nomeAtividade");
        Assertions.assertEquals("nomeAtividade", atividadeDTO.getNomeAtividade());
    }
}
