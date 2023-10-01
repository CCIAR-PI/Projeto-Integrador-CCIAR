package projetointegrador.cciar.projetointegradorcciar.unitTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import projetointegrador.cciar.projetointegradorcciar.dto.EnderecoDTO;
import projetointegrador.cciar.projetointegradorcciar.entity.Pessoa;

@SpringBootTest
class EnderecoTests {

    EnderecoDTO enderecoDTO = new EnderecoDTO();

    EnderecoDTO enderecoDTO1 = new EnderecoDTO(1L, new Pessoa(), "85867-264", "testeLogradouro", "testeLocalidade", "testeBairro", 14, "testeUf");


    @Test
    void testCep (){
        enderecoDTO.setCep("84272-110");
        Assertions.assertEquals("84272-110",enderecoDTO.getCep());
    }
    @Test
    void testCepNulo (){
        enderecoDTO.setCep("84272-110");
        Assertions.assertNotNull(enderecoDTO.getCep());
    }

    @Test
    void testNomeBairro (){
        enderecoDTO.setBairro("testeBairro");
        Assertions.assertEquals("testeBairro", enderecoDTO.getBairro());
    }

    @Test
    void testNomeBairroNulo (){
        enderecoDTO.setBairro("testeBairro");
        Assertions.assertNotNull(enderecoDTO.getBairro());
    }

    @Test
    void testLocalidade (){
        enderecoDTO.setLocalidade("localidadeTeste");
        Assertions.assertEquals("localidadeTeste", enderecoDTO.getLocalidade());
    }

    @Test
    void testLocalidadeNula (){
        enderecoDTO.setLocalidade("localidadeTeste");
        Assertions.assertNotNull(enderecoDTO.getLocalidade());
    }

    @Test
    void testUf (){
        enderecoDTO.setUf("ufTeste");
        Assertions.assertEquals("ufTeste", enderecoDTO.getUf());
    }

    @Test
    void testUfNulo (){
        enderecoDTO.setUf("ufTeste");
        Assertions.assertNotNull(enderecoDTO.getUf());
    }

    @Test
    void testLogradouro (){
        enderecoDTO.setLogradouro("logradouroTest");
        Assertions.assertEquals("logradouroTest", enderecoDTO.getLogradouro());
    }
    @Test
    void testLogradouroNulo (){
        enderecoDTO.setLogradouro("logradouroTest");
        Assertions.assertNotNull(enderecoDTO.getLogradouro());
    }

    @Test
    void testNumCasa (){
        enderecoDTO.setNumCasa(100);
        Assertions.assertEquals(100, enderecoDTO.getNumCasa());
    }

    @Test
    void idTest(){
        enderecoDTO.setId(2L);
        Assertions.assertEquals(2L, enderecoDTO.getId());
    }
}


