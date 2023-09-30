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

import java.time.LocalDateTime;

@SpringBootTest
class PessoaTests {

    Endereco endereco = new Endereco();
    PessoaDTO pessoaDTO = new PessoaDTO();


    @Test
    @DisplayName("Testa se o atributo nome está recebendo o valor esperado")
    void validaNome (){
        Pessoa pessoa = new Pessoa ();
        pessoa.setNome("nomeTeste");
        Assertions.assertEquals("nomeTeste", pessoa.getNome());
    }

    @Test
    @DisplayName("Testa se o atributo nome não está retornando nulo")
    void validaoNomeNulo (){
        pessoaDTO.setNome("nomeTeste");
        Assertions.assertNotNull(pessoaDTO.getNome());
    }

    @Test
    void validaCpf (){
        pessoaDTO.setCpf("117.283.639-69");
        Assertions.assertEquals("117.283.639-69", pessoaDTO.getCpf());
    }

    @Test
    void validaCpfNulo (){
        pessoaDTO.setCpf("117.283.639-69");
        Assertions.assertNotNull(pessoaDTO.getCpf());
    }

    @Test
    void validaRg (){
        pessoaDTO.setRg("49.630.826-9");
        Assertions.assertEquals("49.630.826-9", pessoaDTO.getRg());
    }

    @Test
    void validaRgNulo (){
        pessoaDTO.setRg("49.630.826-9");
        Assertions.assertNotNull(pessoaDTO.getRg());
    }

    @Test
    void validaTelefone (){
        pessoaDTO.setTelefone("(45)99965-6820");
        Assertions.assertEquals("(45)99965-6820", pessoaDTO.getTelefone());
    }

    @Test
    void validaTelefoneNulo (){
        pessoaDTO.setTelefone("(45)99965-6820");
        Assertions.assertNotNull(pessoaDTO.getTelefone());
    }

    @Test
    void validaNacionalidade (){
        pessoaDTO.setNacionalidade("Brasileiro");
        Assertions.assertEquals("Brasileiro", pessoaDTO.getNacionalidade());
    }

    @Test
    void validaNacionalidadeNula (){
        pessoaDTO.setNacionalidade("Brasileiro");
        Assertions.assertNotNull(pessoaDTO.getNacionalidade());
    }

    @Test
    void validaNaturalidade (){
        pessoaDTO.setNaturalidade("Paranaense");
        Assertions.assertEquals("Paranaense", pessoaDTO.getNaturalidade());
    }

    @Test
    void validaNaturalidadeNula (){
        pessoaDTO.setNaturalidade("Paranaense");
        Assertions.assertNotNull(pessoaDTO.getNaturalidade());
    }

    @Test
    void validaSexoM(){
        pessoaDTO.setSexo(Sexo.MASCULINO);
        Assertions.assertEquals(Sexo.MASCULINO, pessoaDTO.getSexo());
    }

    @Test
    void validaSexoF(){
        pessoaDTO.setSexo(Sexo.FEMININO);
        Assertions.assertEquals(Sexo.FEMININO, pessoaDTO.getSexo());
    }

    @Test
    void validaSexoO(){
        pessoaDTO.setSexo(Sexo.OUTRO);
        Assertions.assertEquals(Sexo.OUTRO, pessoaDTO.getSexo());
    }

    @Test
    void validaSexoNulo(){
        pessoaDTO.setSexo(Sexo.MASCULINO);
        Assertions.assertNotNull(pessoaDTO.getSexo());
    }

    @Test
    void validaEscolaridade(){
        pessoaDTO.setEscolaridade(Escolaridade.CURSANDO);
        Assertions.assertEquals(Escolaridade.CURSANDO, pessoaDTO.getEscolaridade());
    }

    @Test
    void validaEscolaridadeNula(){
        pessoaDTO.setEscolaridade(Escolaridade.CURSANDO);
        Assertions.assertNotNull(pessoaDTO.getEscolaridade());
    }

    @Test
    void validaDataCadastro(){
        pessoaDTO.setDataCadastro(LocalDateTime.now());
        Assertions.assertEquals(LocalDateTime.now(), pessoaDTO.getDataCadastro());
    }

    @Test
    void validaDataCadastroNula(){
        pessoaDTO.setDataCadastro(LocalDateTime.now());
        Assertions.assertNotNull(pessoaDTO.getDataCadastro());
    }
}


