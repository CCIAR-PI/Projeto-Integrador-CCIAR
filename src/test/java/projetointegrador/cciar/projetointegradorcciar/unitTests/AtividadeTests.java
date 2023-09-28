package projetointegrador.cciar.projetointegradorcciar.unitTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import projetointegrador.cciar.projetointegradorcciar.dto.AtividadeDTO;

import java.time.LocalDateTime;
import java.time.LocalTime;

@SpringBootTest
class AtividadeTests {
    AtividadeDTO atividadeDTO = new AtividadeDTO();

    @Test
    void testeNomeAtividade (){
        atividadeDTO.setNomeAtividade("nomeAtividade");
        Assertions.assertEquals("nomeAtividade", atividadeDTO.getNomeAtividade());
    }

    @Test
    void testeNomeAtividadeNulo (){
        atividadeDTO.setNomeAtividade("nomeAtividade");
        Assertions.assertNotNull(atividadeDTO.getNomeAtividade());
    }

    @Test
    void testeDescricaoAtividade (){
        atividadeDTO.setDescricao("descricaoAtividade");
        Assertions.assertEquals("descricaoAtividade",atividadeDTO.getDescricao());
    }

    @Test
    void testeDescricaoAtividadeNulo (){
        atividadeDTO.setDescricao("descricaoAtividade");
        Assertions.assertNotNull(atividadeDTO.getDescricao());
    }

    @Test
    void testeHorarioDaAtividade (){

        LocalDateTime data = LocalDateTime.parse("2023-09-28T15:30:45.123456789");

        atividadeDTO.setDataAtividade(data);
        Assertions.assertEquals(data, atividadeDTO.getDataAtividade());
    }

    @Test
    void testeHorarioDaAtividadeNula (){

        LocalDateTime data = LocalDateTime.parse("2023-09-28T15:30:45.123456789");

        atividadeDTO.setDataAtividade(data);
        Assertions.assertNotNull(atividadeDTO.getDataAtividade());
    }

    @Test
    void testeHorarioCadastro (){
        atividadeDTO.setHorarioCadastro(LocalTime.now());
        Assertions.assertEquals(LocalTime.now(),atividadeDTO.getHorarioCadastro());
    }

    @Test
    void testeHorarioCadastroNulo (){
        atividadeDTO.setHorarioCadastro(LocalTime.now());
        Assertions.assertNotNull(atividadeDTO.getHorarioCadastro());
    }




}
