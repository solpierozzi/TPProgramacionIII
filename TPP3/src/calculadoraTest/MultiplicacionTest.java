package calculadoraTest;

import static org.junit.Assert.*;
import intermediario.Intermediario;
import logica.Calculadora;

import org.junit.Test;

public class MultiplicacionTest {
	Calculadora calculadora = new Calculadora();
	Intermediario intermediario = new Intermediario();

	@Test
	public void multiplicacionPositivoPositivo() {
		intermediario.agregar("2.0x4.0");
		assertEquals(Double.valueOf(2.0 * 4.0),calculadora.operar(intermediario.getOperandos(),intermediario.getNumeros()));
	}

	@Test
	public void multiplicacionPositivoNegativo() {
		intermediario.agregar("2.0x-4.0");
		assertEquals(Double.valueOf(2.0 * -4.0),calculadora.operar(intermediario.getOperandos(),intermediario.getNumeros()));
	}

	@Test
	public void multiplicacionNegativoPositivo() {
		intermediario.agregar("-2.0x4.0");
		assertEquals(Double.valueOf(-2.0 * 4.0),calculadora.operar(intermediario.getOperandos(),intermediario.getNumeros()));
	}

	@Test
	public void multiplicacionNegativoNegativo() {
		intermediario.agregar("-2.0x-4.0");
		assertEquals(Double.valueOf(-2.0 * -4.0),calculadora.operar(intermediario.getOperandos(),intermediario.getNumeros()));
	}

	@Test
	public void multiplicacionPositivoPositivo_Decimal() {
		intermediario.agregar("5.0x4.9");
		assertEquals(Double.valueOf(5.0 * 4.9),calculadora.operar(intermediario.getOperandos(),intermediario.getNumeros()));
	}

	@Test
	public void multiplicacionPositivoNegativo_Decimal() {
		intermediario.agregar("5.0x-4.9");
		assertEquals(Double.valueOf(5.0 * -4.9),calculadora.operar(intermediario.getOperandos(),intermediario.getNumeros()));
	}

	@Test
	public void multiplicacionNegativoPositivo_Decimal() {
		intermediario.agregar("-5.2x4.9");
		assertEquals(Double.valueOf(-5.2 * 4.9),calculadora.operar(intermediario.getOperandos(),intermediario.getNumeros()));
	}

	@Test
	public void multiplicacionNegativoNegativo_Decimal() {
		intermediario.agregar("-5.0x-4.9");
		assertEquals(Double.valueOf(-5.0 * -4.9),calculadora.operar(intermediario.getOperandos(),intermediario.getNumeros()));
	}

}