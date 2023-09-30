package projetointegrador.cciar.projetointegradorcciar.integrationTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import projetointegrador.cciar.projetointegradorcciar.controller.AtividadeController;
import projetointegrador.cciar.projetointegradorcciar.dto.AtividadeDTO;
import projetointegrador.cciar.projetointegradorcciar.entity.Atividade;
import projetointegrador.cciar.projetointegradorcciar.repository.AtividadeRepository;
import projetointegrador.cciar.projetointegradorcciar.service.AtividadeService;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class AtividadeIntegrationTests {

    @MockBean
    AtividadeController atividadeController;

    @MockBean
    AtividadeRepository atividadeRepository;

    @Autowired
    AtividadeService atividadeService;

    private List <Atividade> atividadesList;

    @BeforeEach
    public void injectData (){
        atividadesList = new ArrayList<>();
        Atividade atividade1 = new Atividade(1L, true, "testeAtiv", "descricaoAtiv", LocalTime.now(), LocalDateTime.now());
        Atividade atividade2 = new Atividade(1L, true, "testeAtiv2", "descricaoAtiv2", LocalTime.now(), LocalDateTime.now());

        atividadesList.add(atividade1);
        atividadesList.add(atividade2);
    }

    // * Testes controller *//
    @Test
    @DisplayName("Verifica se o retorno do metodo POST é uma mensagem de êxito")
    void  testControllerPost() {
        Mockito.when(atividadeController.cadastrar(Mockito.any(AtividadeDTO.class))).thenReturn(ResponseEntity.ok("Atividade cadastrada com sucesso"));

        var atividade = atividadeController.cadastrar(new AtividadeDTO(1L, true, "testeAtiv", "descricaoAtiv", LocalTime.now(), LocalDateTime.now()));

        Assertions.assertEquals("Atividade cadastrada com sucesso", atividade.getBody());
    }

    @Test
    @DisplayName("Verifica se o retorno do metodo POST é uma mensagem de erro")
    void  testControllerPostError() {

        Mockito.when(atividadeController.editar(Mockito.anyLong(),Mockito.any(AtividadeDTO.class))).thenReturn(ResponseEntity.badRequest().body("Ocorreu um erro durante o cadastro"));
        var atividade = atividadeController.editar(1L, new AtividadeDTO(1L, true, "testeAtiv", "descricaoAtiv", LocalTime.now(), LocalDateTime.now()));

        Assertions.assertEquals("Ocorreu um erro durante o cadastro", atividade.getBody());
    }

    @Test
    @DisplayName("Verifica se o retorno do metodo PUT é uma mensagem de êxito")
    void  testControllerPut() {
        Mockito.when(atividadeController.editar(Mockito.anyLong(), Mockito.any(AtividadeDTO.class))).thenReturn(ResponseEntity.ok("Atividade editada com sucesso"));

        var atividade = atividadeController.editar(1L, new AtividadeDTO(1L, true, "testeAtiv", "descricaoAtiv", LocalTime.now(), LocalDateTime.now()));

        Assertions.assertEquals("Atividade editada com sucesso", atividade.getBody());
    }

    @Test
    @DisplayName("Verifica se o retorno do metodo PUT é uma mensagem de erro")
    void  testControllerPutErro() {
        Mockito.when(atividadeController.editar(Mockito.anyLong(), Mockito.any(AtividadeDTO.class))).thenReturn(ResponseEntity.badRequest().body("Ocorreu um erro"));

        var atividade = atividadeController.editar(1L, new AtividadeDTO(1L, true, "testeAtiv", "descricaoAtiv", LocalTime.now(), LocalDateTime.now()));

        Assertions.assertEquals("Ocorreu um erro", atividade.getBody());
    }

    @Test
    @DisplayName("Verifica se o retorno do metodo DELETE é uma mensagem de êxito")
    void  testControllerDelete() {
        Mockito.when(atividadeController.deletaAtividade(Mockito.anyLong())).thenReturn(ResponseEntity.badRequest().body("Atividade excluída com sucesso"));

        var atividade = atividadeController.deletaAtividade(1L);

        Assertions.assertEquals("Atividade excluída com sucesso", atividade.getBody());
    }

    @Test
    @DisplayName("Verifica se o retorno do metodo DELETE é uma mensagem de erro")
    void  testControllerDeleteErro() {
        Mockito.when(atividadeController.deletaAtividade(Mockito.anyLong())).thenReturn(ResponseEntity.badRequest().body("Ocorreu um erro"));

        var atividade = atividadeController.deletaAtividade(1L);

        Assertions.assertEquals("Ocorreu um erro", atividade.getBody());
    }

    @Test
    @DisplayName("Verifica se o metodo findById está retornando o ID que desejamos")
    void testGetIdAtividade(){
        Mockito.when(atividadeController.findByIDPath(Mockito.anyLong())).thenReturn(ResponseEntity.ok(new Atividade(1L, true, "testeAtiv", "descricaoAtiv", LocalTime.now(), LocalDateTime.now())));
        var atividade = atividadeController.findByIDPath(1L);

        Assertions.assertEquals(1L, atividade.getBody().getId());
    }

    @Test
    @DisplayName("Verifica se ao chamar o método lista, é retornado uma lista de pessoas")
    void testGetAllAtividades(){
        Mockito.when(atividadeController.listaCompleta()).thenReturn(ResponseEntity.ok(atividadesList));

        ResponseEntity<List<Atividade>> atividadeFuncaoController = atividadeController.listaCompleta();
        List<Atividade> atividadeListController = atividadeFuncaoController.getBody();

        for(int i = 0; i < atividadesList.size();i ++){
            Assertions.assertEquals(atividadesList.get(i), atividadeListController.get(i));
        }
    }

    // * Testes Service * //
    @Test
    @DisplayName("Valida a exceção que deve ser exibida caso o registro que deseja editar não exista")
    void testEditarRegistroNaoEncontrado() {
        AtividadeDTO atividadeDTO = new AtividadeDTO();

        Mockito.when(atividadeRepository.findById(1L)).thenReturn(java.util.Optional.empty());
        Assertions.assertThrows(AtividadeService.RegistroNaoEncontradoException.class, () -> atividadeService.editarAtividade(1L, atividadeDTO));
    }

    @Test
    @DisplayName("Valida a exceção que deve ser exibida caso o registro que deseja excluir não exista")
    void testExcluirRegistroNaoEncontrado() {
        AtividadeDTO atividadeDTO = new AtividadeDTO();

        Mockito.when(atividadeRepository.findById(1L)).thenReturn(java.util.Optional.empty());
        Assertions.assertThrows(AtividadeService.RegistroNaoEncontradoException.class, () -> atividadeService.deletarAtividade(1L));
    }

    @Test
    @DisplayName("Verifica se uma atividade é realmente deletada do banco")
    void testDeleteService () {
        Atividade atividade = new Atividade(1L, true, "testeAtiv", "descricaoAtiv", LocalTime.now(), LocalDateTime.now());
        Mockito.when(atividadeRepository.findById(1L)).thenReturn(Optional.of(atividade));

        atividadeService.deletarAtividade(1L);
        Mockito.verify(atividadeRepository).delete(atividade);
    }


    // * Teste repository * //
    @Test
    @DisplayName("Verifica se uma atividade é realmente salva no banco")
    void testSaveBanco (){
        Atividade atividade = new Atividade(1L, true, "testeAtiv", "descricaoAtiv", LocalTime.now(), LocalDateTime.now());
        Mockito.when(atividadeRepository.findById(1L)).thenReturn(java.util.Optional.of(atividade));

        atividadeRepository.save(atividade);
        Mockito.verify(atividadeRepository).save(atividade);
    }
}
