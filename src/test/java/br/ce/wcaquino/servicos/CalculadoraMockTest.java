package br.ce.wcaquino.servicos;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import br.ce.wcaquino.entidades.Locacao;

public class CalculadoraMockTest {
	
	@Mock
	private Calculadora calcMock;

	@Spy
	private Calculadora calcSpy;
	
	@Spy
	private EmailService email;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void deveMostrarDiferencaEntreMockSpy() {
		Mockito.when(calcMock.somar(1, 2)).thenReturn(5);
		Mockito.when(calcSpy.somar(1, 2)).thenReturn(5);
		Mockito.doNothing().when(calcSpy).imprime();
		
		System.out.println("Mock: " + calcMock.somar(1, 2));
		System.out.println("Spy: " + calcSpy.somar(1, 2));
		
		System.out.println("MOCL");
		calcMock.imprime();
		System.out.println("spy");
		calcSpy.imprime();

	}
	
	@Test
	public void teste() {
		Calculadora calc = Mockito.mock(Calculadora.class);
		ArgumentCaptor<Integer> argCapt = ArgumentCaptor.forClass(Integer.class);
		Mockito.when(calc.somar(argCapt.capture(),argCapt.capture())).thenReturn(5);
		Assert.assertEquals(5, calc.somar(1, 2));
//		System.out.println(argCapt.getAllValues());
	}
	
}
