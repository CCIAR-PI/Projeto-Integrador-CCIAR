package projetointegrador.cciar.projetointegradorcciar.unitTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import projetointegrador.cciar.projetointegradorcciar.dto.AdministradorDTO;

@SpringBootTest
class AdministradorTests {

    AdministradorDTO administradorDTO = new AdministradorDTO();

    @Test
    void testNomeAdm (){
        administradorDTO.setNomeAdm("nomeAdm");
        Assertions.assertEquals("nomeAdm", administradorDTO.getNomeAdm());
    }

    @Test
    void testNomeAdmNulo (){
        administradorDTO.setNomeAdm("nomeAdm");
        Assertions.assertNotNull(administradorDTO.getNomeAdm());
    }

    @Test
    void testLoginNomeAdm (){
        administradorDTO.setLoginNome("loginAdm");
        Assertions.assertEquals("loginAdm", administradorDTO.getLoginNome());
    }

    @Test
    void testLoginNomeAdmNulo (){
        administradorDTO.setLoginNome("loginAdm");
        Assertions.assertNotNull( administradorDTO.getLoginNome());
    }

    @Test
    void testSenhaAdm (){
        administradorDTO.setSenha("senha123");
        Assertions.assertEquals("senha123", administradorDTO.getSenha());
    }

    @Test
    void testSenhaAdmNula (){
        administradorDTO.setSenha("senha123");
        Assertions.assertNotNull(administradorDTO.getSenha());
    }

    @Test
    void testCpfAdm (){
        administradorDTO.setCpf("117.283.639-69");
        Assertions.assertEquals("117.283.639-69", administradorDTO.getCpf());
    }

    @Test
    void testCpfAdmNulo (){
        administradorDTO.setCpf("117.283.639-69");
        Assertions.assertNotNull( administradorDTO.getCpf());
    }

    @Test
    void testTelefoneAdm (){
        administradorDTO.setTelefone("(45)99965-6820");
        Assertions.assertEquals("(45)99965-6820", administradorDTO.getTelefone());
    }

    @Test
    void testTelefoneAdmNulo (){
        administradorDTO.setTelefone("(45)99965-6820");
        Assertions.assertNotNull(administradorDTO.getTelefone());
    }

    @Test
    void testEmailAdm (){
        administradorDTO.setEmail("testemail@gmail.com");
        Assertions.assertEquals("testemail@gmail.com", administradorDTO.getEmail());
    }

    @Test
    void testEmailAdmNulo (){
        administradorDTO.setEmail("testemail@gmail.com");
        Assertions.assertNotNull(administradorDTO.getEmail());
    }

    @Test
    void testEmailRecupAdm (){
        administradorDTO.setEmailRecup("testemailrecup@gmail.com");
        Assertions.assertEquals("testemailrecup@gmail.com", administradorDTO.getEmailRecup());
    }

    @Test
    void testEmailRecupAdmNulo (){
        administradorDTO.setEmailRecup("testemailrecup@gmail.com");
        Assertions.assertNotNull(administradorDTO.getEmailRecup());
    }
}
