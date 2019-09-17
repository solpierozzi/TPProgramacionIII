package calculadoraTest;

import static org.junit.Assert.*;
import intermediario.Intermediario;
import logica.Calculadora;

import org.junit.Test;

public class SumaTest {
	Calculadora calculadora = new Calculadora();
	Intermediario intermediario = new Intermediario();

	@Test
	public void sumaPositivoPositivo() {
		intermediario.agregar("7.0+4.0");
		assertEquals(Double.valueOf(7.0 + 4.0),calculadora.operar(intermediario.getOperandos(),intermediario.getNumeros()));
	}

	@Test
	public void sumaNegativoPositivo() {
		intermediario.agregar("-2.0+6.0");
		assertEquals(Double.valueOf(-2.0 + 6.0),calculadora.operar(intermediario.getOperandos(),intermediario.getNumeros()));
	}

	@Test
	public void sumaPositivoNegativo() {
		intermediario.agregar("2.0+-6.0");
		assertEquals(Double.valueOf(2.0 + -6.0),calculadora.operar(intermediario.getOperandos(),intermediario.getNumeros()));
	}

	@Test
	public void sumaNegativoNegativo() {
		intermediario.agregar("-2.0+-4.0");
		assertEquals(Double.valueOf(-2.0 + -4.0),calculadora.operar(intermediario.getOperandos(),intermediario.getNumeros()));
	}

	@Test
	public void sumaPositivoPositivo_Decimal() {
		intermediario.agregar("7.2+2.4");
		assertEquals(Double.valueOf(7.2 + 2.4),calculadora.operar(intermediario.getOperandos(),intermediario.getNumeros()));
	}

	@Test
	public void sumaNegativoPositivo_Decimal() {
		intermediario.agregar("-7.2+2.4");
		assertEquals(Double.valueOf(-7.2 + 2.4),calculadora.operar(intermediario.getOperandos(),intermediario.getNumeros()));
	}

	@Test
	public void sumaPositivoNegativo_Decimal() {
		intermediario.agregar("7.2+-2.4");
		assertEquals(Double.valueOf(7.2 + -2.4),calculadora.operar(intermediario.getOperandos(),intermediario.getNumeros()));
	}

	@Test
	public void sumaNegativoNegativo_Decimal() {
		intermediario.agregar("7.2+2.4");
		assertEquals(Double.valueOf(7.2 + 2.4),calculadora.operar(intermediario.getOperandos(),intermediario.getNumeros()));
	}

	@Test
	public void variasSumasTest() {
		intermediario.agregar("3.0+5.0+8.0+4.0");
		assertEquals(Double.valueOf(3.0 + 5.0 + 8.0 + 4.0),calculadora.operar(intermediario.getOperandos(),intermediario.getNumeros()));
	}

	@Test
	public void variasSumasTest_Decimal() {
		intermediario.agregar("3.5+5.1+8.3+4.0");
		assertEquals(Double.valueOf(3.5 + 5.1 + 8.3 + 4.0),calculadora.operar(intermediario.getOperandos(),intermediario.getNumeros()));
	}

}
