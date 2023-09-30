package projetointegrador.cciar.projetointegradorcciar.integrationTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import projetointegrador.cciar.projetointegradorcciar.controller.PessoaController;
import projetointegrador.cciar.projetointegradorcciar.dto.PessoaDTO;
import projetointegrador.cciar.projetointegradorcciar.entity.Endereco;
import projetointegrador.cciar.projetointegradorcciar.entity.Escolaridade;
import projetointegrador.cciar.projetointegradorcciar.entity.Pessoa;
import projetointegrador.cciar.projetointegradorcciar.entity.Sexo;
import projetointegrador.cciar.projetointegradorcciar.repository.PessoaRepository;
import projetointegrador.cciar.projetointegradorcciar.service.PessoaService;

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

    Endereco endereco = new Endereco(1L, "85867-264","testeLogradouro", "testeLocalidade", "bairroTeste", 10, "PR");

    @BeforeEach
    void injectData() {
        Pessoa pessoa = new Pessoa(1L, "TesteNome", "11728363969", endereco, 1953, "78945612", "(45)99965-6820", "natTest", "nacTest", Escolaridade.CURSANDO, Sexo.MASCULINO, true);
        Pessoa pessoa2 = new Pessoa(1L, "TesteNome2", "11728363969", endereco, 1943, "78945612", "(45)99965-6820", "natTest", "nacTest", Escolaridade.CURSANDO, Sexo.MASCULINO, true);
        pessoaList = new ArrayList<>();
        pessoaList.add(pessoa);
        pessoaList.add(pessoa2);

        Mockito.when(pessoaRepository.save(pessoa)).thenReturn(pessoa);
        Mockito.when(pessoaRepository.save(pessoa2)).thenReturn(pessoa2);
        Mockito.when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));
        Mockito.when(pessoaRepository.findById(2L)).thenReturn(Optional.of(pessoa2));
        Mockito.when(pessoaRepository.findAll()).thenReturn(pessoaList);
    }


    @Test
    void testCpfExistente() {
        PessoaDTO pessoaDTO = new PessoaDTO(1L, "TesteNome", "11728363969", endereco, 1953, "78945612", "(45)99965-6820", "natTest", "nacTest", Escolaridade.CURSANDO, Sexo.MASCULINO, true);
        pessoaDTO.setCpf("11728363969");

        Mockito.when(pessoaRepository.findByCpf(Mockito.any())).thenReturn(new Pessoa());

        Assertions.assertThrows(IllegalArgumentException.class, () -> pessoaService.validaPessoa(pessoaDTO));
    }

    @Test
    void testRgExistente() {
        PessoaDTO pessoaDTO = new PessoaDTO(1L, "TesteNome", "11728363969", endereco, 1953, "78945612", "(45)99965-6820", "natTest", "nacTest", Escolaridade.CURSANDO, Sexo.MASCULINO, true);
        pessoaDTO.setRg("78945612");
        Mockito.when(pessoaRepository.findByRg(Mockito.any())).thenReturn(new Pessoa());

        Assertions.assertNotNull(pessoaDTO);
        Assertions.assertThrows(IllegalArgumentException.class, () -> pessoaService.validaPessoa(pessoaDTO));
    }


    @Test
    @DisplayName("Verifica se o retorno do metodo POST é uma mensagem de êxito")
    void  testControllerPost() {
        Mockito.when(pessoaController.cadastrar(Mockito.any(PessoaDTO.class))).thenReturn(ResponseEntity.ok("Pessoa cadastrada com sucesso"));

        var pessoa = pessoaController.cadastrar(new PessoaDTO(1L, "TesteNome", "11728363969", endereco, 1953, "78945612", "(45)99965-6820", "natTest", "nacTest", Escolaridade.CURSANDO, Sexo.MASCULINO, true));

        Assertions.assertEquals("Pessoa cadastrada com sucesso", pessoa.getBody());
    }

    @Test
    @DisplayName("Verifica se o retorno do metodo POST é uma mensagem de erro")
    void  testControllerPostError() {
        String mensagemDeErro = "Ocorreu um erro durante o cadastro";
        ResponseEntity<String> responseEntity = new ResponseEntity<>(mensagemDeErro, HttpStatus.INTERNAL_SERVER_ERROR);

        Mockito.when(pessoaController.cadastrar(Mockito.any(PessoaDTO.class))).thenReturn(responseEntity);
        var pessoa = pessoaController.cadastrar(new PessoaDTO(1L, "TesteNome", "11728363969", endereco, 1953, "78945612", "(45)99965-6820", "natTest", "nacTest", Escolaridade.CURSANDO, Sexo.MASCULINO, true));

        Assertions.assertEquals(mensagemDeErro, pessoa.getBody());
    }

    @Test
    @DisplayName ("Verifica se o retorno do metodo PUT é uma mensagem de sucesso")
    void testPut () {
        Mockito.when(pessoaController.editar(Mockito.anyLong(), Mockito.any(PessoaDTO.class))).thenReturn(ResponseEntity.ok("Pessoa editada com sucesso"));
        var pessoa = pessoaController.editar(1L, new PessoaDTO(1L, "TesteNome", "11728363969", endereco, 1953, "78945612", "(45)99965-6820", "natTest", "nacTest", Escolaridade.CURSANDO, Sexo.MASCULINO, true));

        Assertions.assertEquals("Pessoa editada com sucesso", pessoa.getBody());
        Assertions.assertNotNull(pessoa);
    }

    @Test
    @DisplayName("Verifica se o retorno do metodo PUT é uma mensagem de erro")
    void  testControllerPutError() {

        Mockito.when(pessoaController.editar(Mockito.anyLong(),Mockito.any(PessoaDTO.class))).thenReturn(ResponseEntity.badRequest().body("Ocorreu um erro durante o cadastro"));
        var pessoa = pessoaController.editar(1L, new PessoaDTO(1L, "TesteNome", "11728363969", endereco, 1953, "78945612", "(45)99965-6820", "natTest", "nacTest", Escolaridade.CURSANDO, Sexo.MASCULINO, true));

        Assertions.assertEquals("Ocorreu um erro durante o cadastro", pessoa.getBody());
    }

    @Test
    @DisplayName("Valida a exceção que deve ser exibida caso o registro que deseja editar não exista")
    public void testPutRegistroNaoEncontrado() {
        PessoaDTO pessoaDTO = new PessoaDTO();

        Mockito.when(pessoaRepository.findById(1L)).thenReturn(java.util.Optional.empty());
        Assertions.assertThrows(PessoaService.RegistroNaoEncontradoException.class, () -> pessoaService.editarPessoa(1L, pessoaDTO));
    }

    @Test
    void testControllerDelete(){
        Mockito.when(pessoaController.deletaPessoa(Mockito.anyLong())).thenReturn(ResponseEntity.ok("Registro excluído"));
        var pessoa = pessoaController.deletaPessoa(1L);

        Assertions.assertEquals("Registro excluído", pessoa.getBody());
    }

    @Test
    void testDeleteUsuarioErro(){
        Mockito.when(pessoaController.deletaPessoa(Mockito.anyLong())).thenReturn(ResponseEntity.badRequest().body("Nao foi possivel identificar o registro informado"));
        var pessoa = pessoaController.deletaPessoa(1L);

        Assertions.assertEquals("Nao foi possivel identificar o registro informado", pessoa.getBody());
    }

    @Test
    @DisplayName("Valida se uma pessoa é realmente deletada do banco")
    void testDeletePessoa() {
        Pessoa pessoa = new Pessoa(1L, "TesteNome", "11728363969", endereco, 1953, "78945612", "(45)99965-6820", "natTest", "nacTest", Escolaridade.CURSANDO, Sexo.MASCULINO, true);
        Mockito.when(pessoaRepository.findById(1L)).thenReturn(java.util.Optional.of(pessoa));

        pessoaService.deletarPessoa(1L);
        Mockito.verify (pessoaRepository).delete(pessoa);
    }

    @Test
    void testGetIdUsuario(){
        Mockito.when(pessoaController.findByIDPath(Mockito.anyLong())).thenReturn(ResponseEntity.ok(new Pessoa(1L, "TesteNome2", "11728363969", endereco, 1943, "78945612", "(45)99965-6820", "natTest", "nacTest", Escolaridade.CURSANDO, Sexo.MASCULINO, true)));

        pessoaController.cadastrar(new PessoaDTO(1L, "TesteNome2", "11728363969", endereco, 1943, "78945612", "(45)99965-6820", "natTest", "nacTest", Escolaridade.CURSANDO, Sexo.MASCULINO, true));
        var pessoa = pessoaController.findByIDPath(1L);

        Assertions.assertEquals(1L, pessoa.getBody().getId());
    }

    @Test
    void testGetAllPessoas(){
        Mockito.when(pessoaController.listaCompleta()).thenReturn(ResponseEntity.ok(pessoaList));

        ResponseEntity<List<Pessoa>> pessoaFuncaoController = pessoaController.listaCompleta();
        List<Pessoa> pessoaListController = pessoaFuncaoController.getBody();

        for(int i = 0; i < pessoaList.size();i ++){
            Assertions.assertEquals(pessoaList.get(i), pessoaListController.get(i));
        }
    }


}
