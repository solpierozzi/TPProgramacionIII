package calculadoraTest;

import static org.junit.Assert.*;
import intermediario.Intermediario;
import logica.Calculadora;

import org.junit.Test;

public class RestaTest {

	Calculadora calculadora = new Calculadora();
	Intermediario intermediario = new Intermediario();

	@Test
	public void restaNegativoNegativo() {
		intermediario.agregar("--2.0--3.0");
		assertEquals(Double.valueOf(-(-2.0) - (-3.0)),calculadora.operar(intermediario.getOperandos(),intermediario.getNumeros()));
	}

	@Test
	public void restaPositivoPositivo() {
		intermediario.agregar("3.0-2.0");
		assertEquals(Double.valueOf(3.0 - 2.0),calculadora.operar(intermediario.getOperandos(),intermediario.getNumeros()));
	}

	@Test
	public void restaPositivoNegativo() {
		intermediario.agregar("2.0--3.0");
		assertEquals(Double.valueOf(2.0 - (-3.0)),calculadora.operar(intermediario.getOperandos(),intermediario.getNumeros()));
	}

	@Test
	public void restaNegativoPositivo() {
		intermediario.agregar("-2.0-3.0");
		assertEquals(Double.valueOf(-2.0 - 3.0),calculadora.operar(intermediario.getOperandos(),intermediario.getNumeros()));
	}

	@Test
	public void restaNegativoNegativo_Decimal() {
		intermediario.agregar("--2.2--3.2");
		assertEquals(Double.valueOf(-(-2.2) - (-3.2)),calculadora.operar(intermediario.getOperandos(),intermediario.getNumeros()));
	}

	@Test
	public void restaPositivoPositivo_Decimal() {
		intermediario.agregar("3.3-2.2");
		assertEquals(Double.valueOf(3.3 - 2.2),calculadora.operar(intermediario.getOperandos(),intermediario.getNumeros()));
	}

	@Test
	public void restaPositivoNegativo_Decimal() {
		intermediario.agregar("2.4--3.2");
		assertEquals(Double.valueOf(2.4 - (-3.2)),calculadora.operar(intermediario.getOperandos(),intermediario.getNumeros()));
	}

	@Test
	public void restaNegativoPositivo_Decimal() {
		intermediario.agregar("-2.2-3.2");
		assertEquals(Double.valueOf(-2.2 - 3.2),calculadora.operar(intermediario.getOperandos(),intermediario.getNumeros()));
	}

	@Test
	public void menosPorMenos_Decimal() {
		intermediario.agregar("--4.4");
		assertEquals(Double.valueOf(-(-4.4)),calculadora.operar(intermediario.getOperandos(),intermediario.getNumeros()));
	}

	@Test
	public void menosPorMenos() {
		intermediario.agregar("--4.0");
		assertEquals(Double.valueOf(-(-4.0)),calculadora.operar(intermediario.getOperandos(),intermediario.getNumeros()));
	}

	@Test
	public void variasRestasTest() {
		intermediario.agregar("-2.0-5.0-7.0");
		assertEquals(Double.valueOf(-2.0 - 5.0 - 7.0),calculadora.operar(intermediario.getOperandos(),intermediario.getNumeros()));
	}

	@Test
	public void variasRestasTest_Decimal() {
		intermediario.agregar("-2.3-5.0-7.2");
		assertEquals(Double.valueOf(-2.3 - 5.0 - 7.2),calculadora.operar(intermediario.getOperandos(),intermediario.getNumeros()));
	}
}