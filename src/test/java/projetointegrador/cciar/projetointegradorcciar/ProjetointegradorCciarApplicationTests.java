package projetointegrador.cciar.projetointegradorcciar;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import projetointegrador.cciar.projetointegradorcciar.entity.Endereco;
import projetointegrador.cciar.projetointegradorcciar.entity.Escolaridade;
import projetointegrador.cciar.projetointegradorcciar.entity.Pessoa;
import projetointegrador.cciar.projetointegradorcciar.entity.Sexo;
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
		Endereco endereco = new Endereco();

		endereco.setUf("PR");
		endereco.setCep("44095-160");
		endereco.setBairro("testeBairro");
		endereco.setLogradouro("testeLogradouro");
		endereco.setNumCasa(123);
		endereco.setLocalidade("testeLocalidade");

		pessoa.setNome("Teste");
		pessoa.setRg("14.215.823-9");
		pessoa.setCpf("202.652.050-00");
		pessoa.setEndereco(endereco);
		pessoa.setSexo(Sexo.MASCULINO);
		pessoa.setCadastroPor("TesteCadastro");
		pessoa.setEscolaridade(Escolaridade.Cursando);
		pessoa.setTelefone("(45)89989-8900");
		pessoa.setNacionalidade("Brasileiro");
		pessoa.setNaturalidade("Paulista");
		pessoa.setDataNascimento(2004);

		Assert.assertEquals("Teste", pessoa.getNome());
		Assert.assertEquals("14.215.823-9", pessoa.getRg());
		Assert.assertEquals("202.652.050-00", pessoa.getCpf());
		Assert.assertEquals(endereco, pessoa.getEndereco());
		Assert.assertEquals(Sexo.MASCULINO, pessoa.getSexo());
		Assert.assertEquals("TesteCadastro", pessoa.getCadastroPor());
		Assert.assertEquals(Escolaridade.Cursando, pessoa.getEscolaridade());
		Assert.assertEquals("(45)89989-8900", pessoa.getTelefone());
		Assert.assertEquals("Brasileiro", pessoa.getNacionalidade());
		Assert.assertEquals("Paulista", pessoa.getNaturalidade());
		Assert.assertEquals(2004, pessoa.getDataNascimento());
	}

}
