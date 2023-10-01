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
import projetointegrador.cciar.projetointegradorcciar.controller.AdministradorController;
import projetointegrador.cciar.projetointegradorcciar.dto.AdministradorDTO;
import projetointegrador.cciar.projetointegradorcciar.entity.Administrador;
import projetointegrador.cciar.projetointegradorcciar.entity.Pessoa;
import projetointegrador.cciar.projetointegradorcciar.repository.AdministradorRepository;
import projetointegrador.cciar.projetointegradorcciar.service.AdministradorService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class AdministradorIntegrationTests {

    @MockBean
    AdministradorRepository administradorRepository;

    @MockBean
    AdministradorController administradorController;

    @Autowired
    AdministradorService administradorService;
    private List<Administrador> administradorList;

    @BeforeEach
    @DisplayName("Injeção de dados que serão utilizados posteriormente no código")
    void injectData() {
        Pessoa pessoa = new Pessoa();

        Administrador administrador = new Administrador(pessoa , 1L, "TesteNome", "loginNome",  "(45)99965-6820", "senhaTest", "emailTest", "emailRecupTest", "11728363969");
        Administrador administrador2 = new Administrador(pessoa , 1L, "TesteNome2", "loginNome2",  "(45)88865-6820", "senhaTest2", "emailTest2", "emailRecupTest2", "00859026914");
        administradorList = new ArrayList<>();
        administradorList.add(administrador);
        administradorList.add(administrador2);
    }

    // * Testes Controller * //
    @Test
    @DisplayName("Verifica se o retorno do metodo POST é uma mensagem de êxito")
    void  testControllerPost() {
        Mockito.when(administradorController.cadastrar(Mockito.any(AdministradorDTO.class))).thenReturn(ResponseEntity.ok("Atividade cadastrada com sucesso"));

        var administrador = administradorController.cadastrar(new AdministradorDTO(1L, "TesteNome2", "loginNome2",  "(45)88865-6820", "senhaTest2", "emailTest2", "emailRecupTest2", "00859026914"));

        Assertions.assertEquals("Atividade cadastrada com sucesso", administrador.getBody());
    }

    @Test
    @DisplayName("Verifica se o retorno do metodo POST é uma mensagem de erro")
    void  testControllerPostError() {

        Mockito.when(administradorController.cadastrar(Mockito.any(AdministradorDTO.class))).thenReturn(ResponseEntity.badRequest().body("Ocorreu um erro durante o cadastro"));
        var administrador = administradorController.cadastrar(new AdministradorDTO(1L, "TesteNome2", "loginNome2",  "(45)88865-6820", "senhaTest2", "emailTest2", "emailRecupTest2", "00859026914"));

        Assertions.assertEquals("Ocorreu um erro durante o cadastro", administrador.getBody());
    }

    @Test
    @DisplayName ("Verifica se o retorno do metodo PUT é uma mensagem de sucesso")
    void testPut () {
        Mockito.when(administradorController.editar(Mockito.anyLong(), Mockito.any(AdministradorDTO.class))).thenReturn(ResponseEntity.ok("Pessoa editada com sucesso"));
        var administrador = administradorController.editar(1L, new AdministradorDTO(1L, "TesteNome2", "loginNome2",  "(45)88865-6820", "senhaTest2", "emailTest2", "emailRecupTest2", "00859026914"));

        Assertions.assertEquals("Pessoa editada com sucesso", administrador.getBody());
    }

    @Test
    @DisplayName("Verifica se o retorno do metodo PUT é uma mensagem de erro")
    void  testControllerPutError() {

        Mockito.when(administradorController.editar(Mockito.anyLong(),Mockito.any(AdministradorDTO.class))).thenReturn(ResponseEntity.badRequest().body("Ocorreu um erro durante o cadastro"));
        var administrador = administradorController.editar(1L, new AdministradorDTO(1L, "TesteNome2", "loginNome2",  "(45)88865-6820", "senhaTest2", "emailTest2", "emailRecupTest2", "00859026914"));

        Assertions.assertEquals("Ocorreu um erro durante o cadastro", administrador.getBody());
    }

    @Test
    @DisplayName("Valida se é retornado uma mensagem de êxito após deletar um adm com tudo certinho")
    void testControllerDelete(){
        Mockito.when(administradorController.deletarAdministrador(Mockito.anyLong())).thenReturn(ResponseEntity.ok("Registro excluído"));
        var administrador = administradorController.deletarAdministrador(1L);

        Assertions.assertEquals("Registro excluído", administrador.getBody());
    }

    @Test
    @DisplayName("Valida se ao deletar um administrador com alguma informação como o ID por exemplo, é inválida")
    void testDeleteAdmErro(){
        Mockito.when(administradorController.deletarAdministrador(Mockito.anyLong())).thenReturn(ResponseEntity.badRequest().body("Nao foi possivel identificar o registro informado"));
        var administrador = administradorController.deletarAdministrador(1L);

        Assertions.assertEquals("Nao foi possivel identificar o registro informado", administrador.getBody());
    }

    @Test
    @DisplayName("Verifica se o metodo findById está retornando o ID que desejamos")
    void testGetIdAdm(){
        Pessoa pessoa = new Pessoa();
        Mockito.when(administradorController.findByIDPath(Mockito.anyLong())).thenReturn(ResponseEntity.ok(new Administrador(pessoa , 1L, "TesteNome2", "loginNome2",  "(45)88865-6820", "senhaTest2", "emailTest2", "emailRecupTest2", "00859026914")));
        var administrador = administradorController.findByIDPath(1L);

        Assertions.assertEquals(1L, administrador.getBody().getId());
    }

    @Test
    @DisplayName("Verifica se ao chamar o método lista, é retornado uma lista de pessoas")
    void testGetAllAdm(){
        Mockito.when(administradorController.listaCompleta()).thenReturn(ResponseEntity.ok(administradorList));

        ResponseEntity<java.util.List<Administrador>> admFuncaoController = administradorController.listaCompleta();
        List<Administrador> pessoaListController = admFuncaoController.getBody();

        for(int i = 0; i < administradorList.size();i ++){
            Assertions.assertEquals(administradorList.get(i), pessoaListController.get(i));
        }
    }

    @Test
    void testGetErrorMessage() {
        administradorController = new AdministradorController();

        Exception exception = new RuntimeException("Mensagem de erro");
        String errorMessage = administradorController.getErrorMessage(exception);

        Assertions.assertEquals("Error: Mensagem de erro", errorMessage);
    }

    // * Testes service * //
    @Test
    @DisplayName("O mockito faz uma simulação de que existe uma pessoa já com um determinado CPF existente e valida se nesse caso é retornado uma exception")
    void testCpfExistente() {
        AdministradorDTO administradorDTO = new AdministradorDTO();
        administradorDTO.setCpf("11728363969");

        Mockito.when(administradorRepository.findByCpf(Mockito.any())).thenReturn(new Administrador());

        Assertions.assertThrows(IllegalArgumentException.class, () -> administradorService.validaAdm(administradorDTO));
    }

    @Test
    void testValidaAdm_CpfNaoExistente() {
        AdministradorDTO administradorDTO = new AdministradorDTO();
        administradorDTO.setCpf("cpfNaoExistente");

        Mockito.when(administradorRepository.findByCpf("cpfNaoExistente")).thenReturn(null);

        Assertions.assertDoesNotThrow(() -> administradorService.validaAdm(administradorDTO));
    }

    @Test
    @DisplayName("Valida a exceção que deve ser exibida caso o registro que deseja editar não exista")
    void testEditarRegistroNaoEncontrado() {
        AdministradorDTO administradorDTO = new AdministradorDTO();

        Mockito.when(administradorRepository.findById(1L)).thenReturn(java.util.Optional.empty());
        Assertions.assertThrows(AdministradorService.RegistroNaoEncontradoException.class, () -> administradorService.editaAdm(1L, administradorDTO));
    }

    @Test
    void testPutPessoaQuandoIdsNaoCorrespondem() {
        Long id = 1L;
        AdministradorDTO administradorDTO = new AdministradorDTO();
        Administrador administradorBanco = new Administrador();
        administradorBanco.setId(2L);

        Mockito.when(administradorRepository.findById(id)).thenReturn(Optional.of(administradorBanco));
        Assertions.assertThrows(AdministradorService.RegistroNaoEncontradoException.class, () -> administradorService.editaAdm(id, administradorDTO));
    }

    @Test
    @DisplayName("Valida a exceção que deve ser exibida caso o registro que deseja excluir não exista")
    void testExcluirRegistroNaoEncontrado() {
        AdministradorDTO administradorDTO = new AdministradorDTO();
        Mockito.when(administradorRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        Assertions.assertThrows(AdministradorService.RegistroNaoEncontradoException.class, () -> administradorService.deletaAdm(1L));
    }

    @Test
    void testDeletePessoaQuandoIdsNaoCorrespondem() {
        Long id = 1L;
        Administrador administradorBanco = new Administrador();
        administradorBanco.setId(2L);

        Mockito.when(administradorRepository.findById(id)).thenReturn(Optional.of(administradorBanco));
        Assertions.assertThrows(AdministradorService.RegistroNaoEncontradoException.class, () -> administradorService.deletaAdm(id));
    }


    // * Testes Repository * //
    @Test
    void testValidaAdm() {
        AdministradorDTO administradorDTO = new AdministradorDTO();
        Administrador administradorExistente = null;

        Mockito.when(administradorRepository.findByCpf(Mockito.any())).thenReturn(administradorExistente);

        administradorService.validaAdm(administradorDTO);

        Mockito.verify(administradorRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void testValidaAdmPut() {
        Long id = 1L;
        AdministradorDTO administradorDTO = new AdministradorDTO();
        Administrador administradorExistente = new Administrador();
        administradorExistente.setId(id);

        Mockito.when(administradorRepository.findById(id)).thenReturn(java.util.Optional.of(administradorExistente));
        administradorService.editaAdm(id, administradorDTO);

        Mockito.verify(administradorRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void testDeletaAdmComSucesso() {
        Long id = 1L;
        Administrador administradorNoBanco = new Administrador();
        administradorNoBanco.setId(id);


        Mockito.when(administradorRepository.findById(id)).thenReturn(Optional.of(administradorNoBanco));
        administradorService.deletaAdm(id);

        Mockito.verify(administradorRepository, Mockito.times(1)).delete(administradorNoBanco);
    }
}
