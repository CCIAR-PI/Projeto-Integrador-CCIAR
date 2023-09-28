package projetointegrador.cciar.projetointegradorcciar.unitTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import projetointegrador.cciar.projetointegradorcciar.dto.PessoaDTO;
import projetointegrador.cciar.projetointegradorcciar.entity.Endereco;
import projetointegrador.cciar.projetointegradorcciar.entity.Escolaridade;
import projetointegrador.cciar.projetointegradorcciar.entity.Pessoa;
import projetointegrador.cciar.projetointegradorcciar.entity.Sexo;

@SpringBootTest
class PessoaTests {

    Endereco endereco = new Endereco();
    PessoaDTO pessoaDTO = new PessoaDTO(1L, "TesteNome", "117.283.639-69", endereco, 27, "8888888", "(45)99965-6820", "Brasileiro", "Brasilian", Escolaridade.CURSANDO, Sexo.MASCULINO, true);


    @Test
    @DisplayName("Testa se o atributo nome está recebendo o valor esperado e se não está retornando nulo")
    void validaCadastroNome (){
        Pessoa pessoa = new Pessoa ();
        pessoa.setNome("nomeTeste");
        Assertions.assertEquals("nomeTeste", pessoa.getNome());
    }

    @Test
    @DisplayName("Testa se o atributo nome está recebendo o valor esperado e se não está retornando nulo")
    void validaCadastroNomeNulo (){
        pessoaDTO.setNome("nomeTeste");
        Assertions.assertNotNull(pessoaDTO.getNome());
    }

    @Test
    void validaCadastroCpf (){
        pessoaDTO.setCpf("117.283.639-69");
        Assertions.assertEquals("117.283.639-69", pessoaDTO.getCpf());

    }
}


