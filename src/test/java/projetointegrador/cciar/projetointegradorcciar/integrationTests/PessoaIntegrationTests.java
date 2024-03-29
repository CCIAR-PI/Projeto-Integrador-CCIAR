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
import projetointegrador.cciar.projetointegradorcciar.controller.PessoaController;
import projetointegrador.cciar.projetointegradorcciar.dto.PessoaDTO;
import projetointegrador.cciar.projetointegradorcciar.entity.*;
import projetointegrador.cciar.projetointegradorcciar.repository.PessoaRepository;
import projetointegrador.cciar.projetointegradorcciar.service.PessoaService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class PessoaIntegrationTests {

    @MockBean
    PessoaController pessoaController;

    @MockBean
    PessoaRepository pessoaRepository;

    @Autowired
    PessoaService pessoaService;

    private List<Pessoa> pessoaList;
    Endereco endereco = new Endereco(1L, "85867-264", "testeLogradouro", "testeLocalidade", "bairroTeste", 10, "PR");

    @BeforeEach
    @DisplayName("Injeção de dados que serão utilizados posteriormente no código")
    void injectData() {
        Administrador administrador = new Administrador();
        Pessoa pessoa = new Pessoa(1L, "TesteNome", "11728363969", endereco, 1953, "78945612", "(45)99965-6820", "natTest", "nacTest", Escolaridade.CURSANDO, Sexo.MASCULINO, administrador, LocalDateTime.now(), LocalDateTime.now(), true);
        Pessoa pessoa2 = new Pessoa(1L, "TesteNome", "000859026914", endereco, 1943, "78945612", "(45)88865-6820", "natTest", "nacTest", Escolaridade.CURSANDO, Sexo.MASCULINO, administrador, LocalDateTime.now(), LocalDateTime.now(), true);
        pessoaList = new ArrayList<>();
        pessoaList.add(pessoa);
        pessoaList.add(pessoa2);
    }

    // * Testes Controller * //
    @Test
    @DisplayName("Verifica se o retorno do metodo POST é uma mensagem de êxito")
    void testControllerPost() {
        Mockito.when(pessoaController.cadastrar(Mockito.any(PessoaDTO.class))).thenReturn(ResponseEntity.ok("Pessoa cadastrada com sucesso"));

        var pessoa = pessoaController.cadastrar(new PessoaDTO());

        Assertions.assertEquals("Pessoa cadastrada com sucesso", pessoa.getBody());
    }

    @Test
    @DisplayName("Verifica se o retorno do metodo POST é uma mensagem de erro")
    void testControllerPostError() {

        Mockito.when(pessoaController.cadastrar(Mockito.any(PessoaDTO.class))).thenReturn(ResponseEntity.badRequest().body("Ocorreu um erro durante o cadastro"));
        var pessoa = pessoaController.cadastrar(new PessoaDTO());

        Assertions.assertEquals("Ocorreu um erro durante o cadastro", pessoa.getBody());
    }

    @Test
    @DisplayName("Verifica se o retorno do metodo PUT é uma mensagem de sucesso")
    void testPut() {
        Mockito.when(pessoaController.editar(Mockito.anyLong(), Mockito.any(PessoaDTO.class))).thenReturn(ResponseEntity.ok("Pessoa editada com sucesso"));
        var pessoa = pessoaController.editar(1L, new PessoaDTO());

        Assertions.assertEquals("Pessoa editada com sucesso", pessoa.getBody());
        Assertions.assertNotNull(pessoa);
    }

    @Test
    @DisplayName("Verifica se o retorno do metodo PUT é uma mensagem de erro")
    void testControllerPutError() {

        Mockito.when(pessoaController.editar(Mockito.anyLong(), Mockito.any(PessoaDTO.class))).thenReturn(ResponseEntity.badRequest().body("Ocorreu um erro durante o cadastro"));
        var pessoa = pessoaController.editar(1L, new PessoaDTO());

        Assertions.assertEquals("Ocorreu um erro durante o cadastro", pessoa.getBody());
    }

    @Test
    @DisplayName("Valida se é retornado uma mensagem de êxito após deletar uma pessoa com tudo certinho")
    void testControllerDelete() {
        Mockito.when(pessoaController.deletaPessoa(Mockito.anyLong())).thenReturn(ResponseEntity.ok("Registro excluído"));
        var pessoa = pessoaController.deletaPessoa(1L);

        Assertions.assertEquals("Registro excluído", pessoa.getBody());
    }

    @Test
    @DisplayName("Valida se ao deletar uma pessoa com alguma informação como o ID por exemplo, é inválida")
    void testDeletePessoaErro() {
        Mockito.when(pessoaController.deletaPessoa(Mockito.anyLong())).thenReturn(ResponseEntity.badRequest().body("Nao foi possivel identificar o registro informado"));
        var pessoa = pessoaController.deletaPessoa(1L);

        Assertions.assertEquals("Nao foi possivel identificar o registro informado", pessoa.getBody());
    }

    @Test
    @DisplayName("Verifica se o metodo findById está retornando o ID que desejamos")
    void testGetIdUsuario() {
        Mockito.when(pessoaController.findByIDPath(Mockito.anyLong())).thenReturn(ResponseEntity.ok(new Pessoa(1L, "TesteNome", "000859026914", endereco, 1943, "78945612", "(45)88865-6820", "natTest", "nacTest", Escolaridade.CURSANDO, Sexo.MASCULINO, new Administrador(), LocalDateTime.now(), LocalDateTime.now(), true)));

        pessoaController.cadastrar(new PessoaDTO());
        var pessoa = pessoaController.findByIDPath(1L);

        Assertions.assertEquals(1L, pessoa.getBody().getId());
    }

    @Test
    @DisplayName("Verifica se ao chamar o método lista, é retornado uma lista de pessoas")
    void testGetAllPessoas() {
        Mockito.when(pessoaController.listaCompleta()).thenReturn(ResponseEntity.ok(pessoaList));

        ResponseEntity<List<Pessoa>> pessoaFuncaoController = pessoaController.listaCompleta();
        List<Pessoa> pessoaListController = pessoaFuncaoController.getBody();

        for (int i = 0; i < pessoaList.size(); i++) {
            Assertions.assertEquals(pessoaList.get(i), pessoaListController.get(i));
        }
    }

    @Test
    void testGetErrorMessage() {
        pessoaController = new PessoaController();

        Exception exception = new RuntimeException("Mensagem de erro");
        String errorMessage = pessoaController.getErrorMessage(exception);

        Assertions.assertEquals("Error: Mensagem de erro", errorMessage);
    }

    // * Testes service * //
    @Test
    @DisplayName("Valida a exceção que deve ser exibida caso o registro que deseja editar não exista")
    void testPutRegistroNaoEncontrado() {
        PessoaDTO pessoaDTO = new PessoaDTO();

        Mockito.when(pessoaRepository.findById(1L)).thenReturn(java.util.Optional.empty());
        Assertions.assertThrows(PessoaService.RegistroNaoEncontradoException.class, () -> pessoaService.editarPessoa(1L, pessoaDTO));
    }

    @Test
    void testEditarPessoaQuandoIdsNaoCorrespondemPUT() {
        Long id = 1L;
        PessoaDTO pessoaDTO = new PessoaDTO();
        Pessoa pessoaBanco = new Pessoa();
        pessoaBanco.setId(2L);


        Mockito.when(pessoaRepository.findById(id)).thenReturn(Optional.of(pessoaBanco));
        Assertions.assertThrows(PessoaService.RegistroNaoEncontradoException.class, () -> pessoaService.editarPessoa(id, pessoaDTO));
    }

    @Test
    @DisplayName("Valida a exceção que deve ser exibida caso o registro que deseja excluir não exista")
    void testDeleteRegistroNaoEncontrado() {
        Mockito.when(pessoaRepository.findById(1L)).thenReturn(java.util.Optional.empty());
        Assertions.assertThrows(PessoaService.RegistroNaoEncontradoException.class, () -> pessoaService.deletarPessoa(1L));
    }

    @Test
    void testDeletePessoaQuandoIdsNaoCorrespondem() {
        Long id = 1L;
        Pessoa pessoaBanco = new Pessoa();
        pessoaBanco.setId(2L);


        Mockito.when(pessoaRepository.findById(id)).thenReturn(Optional.of(pessoaBanco));
        Assertions.assertThrows(PessoaService.RegistroNaoEncontradoException.class, () -> pessoaService.deletarPessoa(id));
    }

    @Test
    @DisplayName("Valida se uma pessoa é realmente deletada do banco")
    void testDeletePessoa() {
        Pessoa pessoa = new Pessoa(1L, "TesteNome", "000859026914", endereco, 1943, "78945612", "(45)88865-6820", "natTest", "nacTest", Escolaridade.CURSANDO, Sexo.MASCULINO, new Administrador(), LocalDateTime.now(), LocalDateTime.now(), true);
        Mockito.when(pessoaRepository.findById(1L)).thenReturn(java.util.Optional.of(pessoa));

        pessoaService.deletarPessoa(1L);
        Mockito.verify(pessoaRepository).delete(pessoa);
    }

    @Test
    @DisplayName("O mockito faz uma simulação de que existe uma pessoa já com um determinado CPF existente e valida se nesse caso é retornado uma exception")
    void testCpfExistente() {
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setCpf("11728363969");

        Mockito.when(pessoaRepository.findByCpf(Mockito.any())).thenReturn(new Pessoa());

        Assertions.assertThrows(IllegalArgumentException.class, () -> pessoaService.validaPessoa(pessoaDTO));
    }

    @Test
    void testValidaPessoaCpfExistente() {
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setCpf("CPF_EXISTENTE");

        // Simule que já existe uma pessoa com o CPF especificado no repositório
        Mockito.when(pessoaRepository.findByCpf("CPF_EXISTENTE")).thenReturn(new Pessoa());

        // Verifique se uma exceção é lançada quando tentamos cadastrar uma pessoa com CPF existente
        Assertions.assertThrows(IllegalArgumentException.class, () -> pessoaService.validaPessoa(pessoaDTO));
    }

    @Test
    void testValidaPessoaCpfNulo() {
        PessoaDTO pessoaDTO = new PessoaDTO();

        // Simule que não há nenhuma pessoa com o CPF especificado no repositório
        Mockito.when(pessoaRepository.findByCpf(null)).thenReturn(null);

        // Não deve lançar exceção quando o CPF é nulo
        Assertions.assertDoesNotThrow(() -> pessoaService.validaPessoa(pessoaDTO));
    }
@Test
    void testValidaPessoaCpfNaoExistente() {
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setCpf("cpfNaoExistente");

        Mockito.when(pessoaRepository.findByCpf("cpfNaoExistente")).thenReturn(null);

        Assertions.assertDoesNotThrow(() -> pessoaService.validaPessoa(pessoaDTO));
    }

    @Test
    @DisplayName("O mockito faz uma simulação de que existe uma pessoa já com um determinado RG existente e valida se nesse caso é retornado uma exception")
    void testRgExistente() {
        PessoaDTO pessoaDTO = new PessoaDTO();
        Mockito.when(pessoaRepository.findByRg(Mockito.any())).thenReturn(new Pessoa());

        Assertions.assertNotNull(pessoaDTO);
        Assertions.assertThrows(IllegalArgumentException.class, () -> pessoaService.validaPessoa(pessoaDTO));
    }
    @Test
    void testValidaPessoaRgNaoExistente() {
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setRg("RgNaoExistente");

        Mockito.when(pessoaRepository.findByRg("RgNaoExistente")).thenReturn(null);

        Assertions.assertDoesNotThrow(() -> pessoaService.validaPessoa(pessoaDTO));
    }

    // * Testes Repository * //
    @Test
     void testValidaPessoa() {
        PessoaDTO pessoaDTO = new PessoaDTO();
        Pessoa pessoaExistente = null;

        Mockito.when(pessoaRepository.findByCpf(Mockito.any())).thenReturn(pessoaExistente);

        pessoaService.validaPessoa(pessoaDTO);

        Mockito.verify(pessoaRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void testValidaPessoaPut() {
        Long id = 1L;
        PessoaDTO pessoaDTO = new PessoaDTO(); // Crie um objeto PessoaDTO com os dados necessários para o teste
        Pessoa pessoaExistente = new Pessoa(); // Suponha que não exista nenhuma Pessoa com o mesmo CPF no repositório
        pessoaExistente.setId(id);

        Mockito.when(pessoaRepository.findById(id)).thenReturn(java.util.Optional.of(pessoaExistente));
        pessoaService.editarPessoa(id, pessoaDTO);

        // Verifique se o método save foi chamado com o objeto Pessoa correto
        Mockito.verify(pessoaRepository, Mockito.times(1)).save(Mockito.any());
    }


}
