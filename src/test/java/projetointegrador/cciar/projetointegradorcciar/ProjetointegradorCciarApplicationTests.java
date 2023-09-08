package projetointegrador.cciar.projetointegradorcciar;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import projetointegrador.cciar.projetointegradorcciar.entity.Pessoa;
import projetointegrador.cciar.projetointegradorcciar.service.PessoaService;

@SpringBootTest
class ProjetointegradorCciarApplicationTests {

	@Autowired
	private PessoaService pessoaService;

	@Test
	void contextLoads() {
	}

	@Test
	public void testPessoa (){
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Teste");

		Assert.assertEquals("pao", pessoa.getNome());

	}

}
