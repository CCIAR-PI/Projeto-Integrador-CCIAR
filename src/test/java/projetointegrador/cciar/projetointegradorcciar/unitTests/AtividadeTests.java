package projetointegrador.cciar.projetointegradorcciar.unitTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import projetointegrador.cciar.projetointegradorcciar.dto.AtividadeDTO;
import projetointegrador.cciar.projetointegradorcciar.entity.Atividade;

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

    @Test
    void idTest (){
        atividadeDTO.setId(1L);
        Assertions.assertEquals(1L, atividadeDTO.getId());
    }

    @Test
    void testConstructorAndGetters() {
        Long id = 1L;
        boolean ativo = true;
        String nomeAtividade = "Exemplo Atividade";
        String descricao = "Descrição da atividade";
        LocalTime horarioCadastro = LocalTime.now();
        LocalDateTime dataAtividade = LocalDateTime.now();

        Atividade atividade = new Atividade(id, ativo, nomeAtividade, descricao, horarioCadastro, dataAtividade);

        Assertions.assertEquals(id, atividade.getId());
        Assertions.assertEquals(ativo, atividade.isAtivo());
        Assertions.assertEquals(nomeAtividade, atividade.getNomeAtividade());
        Assertions.assertEquals(descricao, atividade.getDescricao());
        Assertions.assertEquals(horarioCadastro, atividade.getHorarioCadastro());
        Assertions.assertEquals(dataAtividade, atividade.getDataAtividade());
    }

    @Test
    void testDefaultConstructor() {
        Atividade atividade = new Atividade();

        Assertions.assertNull(atividade.getId());
        Assertions.assertFalse(atividade.isAtivo());
        Assertions.assertNull(atividade.getNomeAtividade());
        Assertions.assertNull(atividade.getDescricao());
        Assertions.assertNull(atividade.getHorarioCadastro());
        Assertions.assertNull(atividade.getDataAtividade());
    }
}
