package br.ce.wcaquino.servicos;

import static org.hamcrest.CoreMatchers.is;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.ce.wcaquino.builders.FilmeBuilder;
import br.ce.wcaquino.builders.UsuarioBuilder;
import br.ce.wcaquino.daos.LocacaoDAO;
import br.ce.wcaquino.daos.LocacaoDAOFake;
import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;

@RunWith(Parameterized.class)
public class CalculoValorLocacaoTeste {

	@Parameter
	public List<Filme> filmes;
	
	@Parameter(value=1)
	public Double ValorLocacao;
	
	@Parameter(value=2)
	public String cenario;
	
	@InjectMocks
	private LocacaoService service;
	
	@Mock
	private LocacaoDAO dao;
	
	@Mock
	private SPCService spc;
	

	private static Filme filme1 =  FilmeBuilder.umFilme().agora();
	private static Filme filme2 =  FilmeBuilder.umFilme().agora();
	private static Filme filme3 =  FilmeBuilder.umFilme().agora();
	private static Filme filme4 =  FilmeBuilder.umFilme().agora();
	private static Filme filme5 =  FilmeBuilder.umFilme().agora();

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Parameters(name="{2}")
	public static Collection<Object[]> getParametros(){
		return Arrays.asList(new Object[][] {
			{Arrays.asList(filme1, filme2, filme3), 11.0, "3 filmes 25%"},
			{Arrays.asList(filme1, filme2, filme3,filme4), 13.0, "4filmes 50%"},
			{Arrays.asList(filme1, filme2, filme3,filme4,filme5), 14.0,"5 filmes 75%"},

		});
	}
	
	@Test
	public void deveCalcularValorLocacaoConsiderandoDescontos() throws FilmeSemEstoqueException, LocadoraException {
		//cenario
		Usuario usuario = UsuarioBuilder.umUsuario().agora();
		
		//acao
		Locacao resultado = service.alugarFilme(usuario, filmes);
		//verificacao
		Assert.assertThat(resultado.getValor(), is(ValorLocacao));
	}
	
	@Test
	public void print() {
		System.out.println(ValorLocacao);
	}
	
	
}
