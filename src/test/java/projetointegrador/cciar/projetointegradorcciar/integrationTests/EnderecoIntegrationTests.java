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
import projetointegrador.cciar.projetointegradorcciar.controller.EnderecoController;
import projetointegrador.cciar.projetointegradorcciar.dto.EnderecoDTO;
import projetointegrador.cciar.projetointegradorcciar.entity.Endereco;
import projetointegrador.cciar.projetointegradorcciar.entity.Pessoa;
import projetointegrador.cciar.projetointegradorcciar.repository.EnderecoRepository;
import projetointegrador.cciar.projetointegradorcciar.service.EnderecoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class EnderecoIntegrationTests {
    @MockBean
    EnderecoController enderecoController;

    @MockBean
    EnderecoRepository enderecoRepository;

    @Autowired
    EnderecoService enderecoService;

    private List<Endereco> enderecoList;

    @BeforeEach
    public void injectData (){
        Pessoa pessoa = new Pessoa();
        enderecoList = new ArrayList<>();
        Endereco endereco1 = new Endereco(1L, "85867-264", "testeLogradouro", "testeLocalidade", "testeBairro", 14, "testeUf");
        Endereco endereco2 = new Endereco(2L, "85867-278", "testeLogradouro2", "testeLocalidade2", "testeBairr2o", 14, "testeUf2");

        enderecoList.add(endereco1);
        enderecoList.add(endereco2);
    }

    // * Testes controller *//
    @Test
    @DisplayName("Verifica se o retorno do metodo POST é uma mensagem de êxito")
    void  testControllerPost() {
        Mockito.when(enderecoController.cadastrar(Mockito.any(EnderecoDTO.class))).thenReturn(ResponseEntity.ok("Endereco cadastrado com sucesso"));

        Pessoa pessoa = new Pessoa();
        var endereco = enderecoController.cadastrar(new EnderecoDTO(pessoa, "85867-264", "testeLogradouro", "testeLocalidade", "testeBairro", 14, "testeUf"));

        Assertions.assertEquals("Endereco cadastrado com sucesso", endereco.getBody());
    }

    @Test
    @DisplayName("Verifica se o retorno do metodo POST é uma mensagem de erro")
    void  testControllerPostError() {

        Mockito.when(enderecoController.editar(Mockito.anyLong(),Mockito.any(EnderecoDTO.class))).thenReturn(ResponseEntity.badRequest().body("Ocorreu um erro durante o cadastro"));
        var endereco = enderecoController.editar(1L, new EnderecoDTO(new Pessoa(), "85867-264", "testeLogradouro", "testeLocalidade", "testeBairro", 14, "testeUf"));

        Assertions.assertEquals("Ocorreu um erro durante o cadastro", endereco.getBody());
    }

    @Test
    @DisplayName("Verifica se o retorno do metodo PUT é uma mensagem de êxito")
    void  testControllerPut() {
        Mockito.when(enderecoController.editar(Mockito.anyLong(), Mockito.any(EnderecoDTO.class))).thenReturn(ResponseEntity.ok("Atividade editada com sucesso"));

        var endereco = enderecoController.editar(1L, new EnderecoDTO(new Pessoa(), "85867-264", "testeLogradouro", "testeLocalidade", "testeBairro", 14, "testeUf"));

        Assertions.assertEquals("Atividade editada com sucesso", endereco.getBody());
    }

    @Test
    @DisplayName("Verifica se o retorno do metodo PUT é uma mensagem de erro")
    void  testControllerPutErro() {
        Mockito.when(enderecoController.editar(Mockito.anyLong(), Mockito.any(EnderecoDTO.class))).thenReturn(ResponseEntity.badRequest().body("Ocorreu um erro"));

        var endereco = enderecoController.editar(1L, new EnderecoDTO(new Pessoa(), "85867-264", "testeLogradouro", "testeLocalidade", "testeBairro", 14, "testeUf"));

        Assertions.assertEquals("Ocorreu um erro", endereco.getBody());
    }

    @Test
    @DisplayName("Verifica se o retorno do metodo DELETE é uma mensagem de êxito")
    void  testControllerDelete() {
        Mockito.when(enderecoController.deletaEndereco(Mockito.anyLong())).thenReturn(ResponseEntity.badRequest().body("Endereço excluído com sucesso"));

        var endereco = enderecoController.deletaEndereco(1L);

        Assertions.assertEquals("Endereço excluído com sucesso", endereco.getBody());
    }

    @Test
    @DisplayName("Verifica se o retorno do metodo DELETE é uma mensagem de erro")
    void  testControllerDeleteErro() {
        Mockito.when(enderecoController.deletaEndereco(Mockito.anyLong())).thenReturn(ResponseEntity.badRequest().body("Ocorreu um erro"));

        var endereco = enderecoController.deletaEndereco(1L);

        Assertions.assertEquals("Ocorreu um erro", endereco.getBody());
    }

    @Test
    @DisplayName("Verifica se o metodo findById está retornando o ID que desejamos")
    void testGetIdEndereco(){
        Mockito.when(enderecoController.findByIDPath(Mockito.anyLong())).thenReturn(ResponseEntity.ok(new Endereco(1L,"85867-264", "testeLogradouro", "testeLocalidade", "testeBairro", 14, "testeUf")));
        var endereco = enderecoController.findByIDPath(1L);

        Assertions.assertEquals(1L, endereco.getBody().getId());
    }

    @Test
    @DisplayName("Verifica se ao chamar o método lista, é retornado uma lista de endereços")
    void testGetAllEnderecos(){
        Mockito.when(enderecoController.listaCompleta()).thenReturn(ResponseEntity.ok(enderecoList));

        ResponseEntity<List<Endereco>> enderecoFuncaoController = enderecoController.listaCompleta();
        List<Endereco> enderecoListController = enderecoFuncaoController.getBody();

        for(int i = 0; i < enderecoList.size();i ++){
            Assertions.assertEquals(enderecoList.get(i), enderecoListController.get(i));
        }
    }

    // * Testes Service * //

    @Test
    @DisplayName("Valida a exceção que deve ser exibida caso o registro que deseja editar não exista")
    void testEditarRegistroNaoEncontrado() {
        EnderecoDTO enderecoDTO = new EnderecoDTO();

        Mockito.when(enderecoRepository.findById(1L)).thenReturn(java.util.Optional.empty());
        Assertions.assertThrows(EnderecoService.RegistroNaoEncontradoException.class, () -> enderecoService.editarEndereco(1L, enderecoDTO));
    }



    @Test
    @DisplayName("Valida a exceção que deve ser exibida caso o registro que deseja excluir não exista")
    void testExcluirRegistroNaoEncontrado() {
        EnderecoDTO enderecoDTO = new EnderecoDTO();

        Mockito.when(enderecoRepository.findById(1L)).thenReturn(java.util.Optional.empty());
        Assertions.assertThrows(EnderecoService.RegistroNaoEncontradoException.class, () -> enderecoService.deletarEndereco(1L));
    }

    @Test
    @DisplayName("Verifica se uma atividade é realmente deletada do banco")
    void testDeleteService () {
        Endereco endereco = new Endereco(1L, "85867-264", "testeLogradouro", "testeLocalidade", "testeBairro", 14, "testeUf");
        Mockito.when(enderecoRepository.findById(1L)).thenReturn(Optional.of(endereco));

        enderecoService.deletarEndereco(1L);
        Mockito.verify(enderecoRepository).delete(endereco);
    }

    // * Testes Repository * //

    @Test
    @DisplayName("Verifica se uma atividade é realmente salva no banco")
    void testSaveBanco (){
        Endereco endereco = new Endereco(1L, "85867-264", "testeLogradouro", "testeLocalidade", "testeBairro", 14, "testeUf");
        Mockito.when(enderecoRepository.findById(1L)).thenReturn(java.util.Optional.of(endereco));

        enderecoRepository.save(endereco);
        Mockito.verify(enderecoRepository).save(endereco);
    }
}
