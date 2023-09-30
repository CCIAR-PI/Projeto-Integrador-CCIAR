package projetointegrador.cciar.projetointegradorcciar;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import projetointegrador.cciar.projetointegradorcciar.entity.Pessoa;
@SpringBootTest
class ProjetointegradorCciarApplicationTests {

	@Test
	public void testPessoa () {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Teste");

        Assertions.assertEquals("Teste", pessoa.getNome());
        Assertions.assertNotNull(pessoa);
    }
}
