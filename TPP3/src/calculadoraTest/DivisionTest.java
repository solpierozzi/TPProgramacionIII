package calculadoraTest;

import static org.junit.Assert.*;
import intermediario.Intermediario;
import logica.Calculadora;

import org.junit.Test;

public class DivisionTest {
	Calculadora calculadora = new Calculadora();
	Intermediario intermediario = new Intermediario();

	@Test
	public void divisionEnteraTest() {
		intermediario.agregar("10.0/5.0");
		assertEquals(Double.valueOf(10.0 / 5.0),calculadora.operar(intermediario.getOperandos(),intermediario.getNumeros()));
	}

	@Test
	public void divisionDecimalTest() {
		intermediario.agregar("10.5/3.5");
		assertEquals(Double.valueOf(10.5 / 3.5),calculadora.operar(intermediario.getOperandos(),intermediario.getNumeros()));
	}

	@Test
	public void divisionPositivoNegativo() {
		intermediario.agregar("10.5/-3.5");
		assertEquals(Double.valueOf(10.5 / -3.5),calculadora.operar(intermediario.getOperandos(),intermediario.getNumeros()));
	}

	@Test
	public void divisionNegativoPositivo() {
		intermediario.agregar("-10.5/3.5");
		assertEquals(Double.valueOf(-10.5 / 3.5),calculadora.operar(intermediario.getOperandos(),intermediario.getNumeros()));
	}

	@Test
	public void divisionNegativoNegativo() {
		intermediario.agregar("-10.5/-3.5");
		assertEquals(Double.valueOf(-10.5 / -3.5),calculadora.operar(intermediario.getOperandos(),intermediario.getNumeros()));
	}

	@Test(expected = java.lang.ArithmeticException.class)
	public void denominadorCero() {
		intermediario.agregar("3.0/0.0");
		calculadora.operar(intermediario.getOperandos(),intermediario.getNumeros());
	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void numeradorCero() {
		intermediario.agregar("0.0/3.0");
		calculadora.operar(intermediario.getOperandos(),intermediario.getNumeros());
	}

}