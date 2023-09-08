package projetointegrador.cciar.projetointegradorcciar;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import projetointegrador.cciar.projetointegradorcciar.entity.*;

@SpringBootTest
class ProjetointegradorCciarApplicationTests {

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

	@Test
	public void testAtividade () {
		Atividade atividade = new Atividade();

		atividade.setNomeAtividade("TesteAtividade");
		atividade.setDescricao("DescricaoAtividade");

		Assert.assertEquals("TesteAtividade", atividade.getNomeAtividade());
		Assert.assertEquals("DescricaoAtividade", atividade.getDescricao());
	}


	@Test
	public void testAdministrador (){
		Administrador administrador = new Administrador();

		administrador.setLoginNome("LoginAdm");
		administrador.setEmail("EmailAdm");
		administrador.setSenha("SenhaAdm");
		administrador.setEmailRecup("EmailRecupAdm");
		administrador.setTelefone("(45)99958-4575");
		administrador.setCpf("399.478.260-07");

		Assert.assertEquals("LoginAdm", administrador.getLoginNome());
		Assert.assertEquals("EmailAdm", administrador.getEmail());
		Assert.assertEquals("SenhaAdm", administrador.getSenha());
		Assert.assertEquals("EmailRecupAdm", administrador.getEmailRecup());
		Assert.assertEquals("(45)99958-4575", administrador.getTelefone());
		Assert.assertEquals("399.478.260-07", administrador.getCpf());
	}
}
