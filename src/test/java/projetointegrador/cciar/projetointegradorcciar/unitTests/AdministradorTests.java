package projetointegrador.cciar.projetointegradorcciar.unitTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import projetointegrador.cciar.projetointegradorcciar.dto.AdministradorDTO;

@SpringBootTest
class AdministradorTests {

    AdministradorDTO administradorDTO = new AdministradorDTO();

    @Test
    void testNome (){
        administradorDTO.setNomeAdm("testeNomeAdm");
        Assertions.assertEquals("testeNomeAdm", administradorDTO.getNomeAdm());
    }
}
