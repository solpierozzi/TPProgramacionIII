package calculadoraTest;

import static org.junit.Assert.*;
import intermediario.Intermediario;
import logica.Calculadora;

import org.junit.Test;

public class CombinadosTest {
	Intermediario intermediario = new Intermediario();
	Calculadora calculadora = new Calculadora();

	@Test
	public void calculoCombinadoTest() {
		intermediario.agregar("-4.0+3.0x2.0");
		assertEquals(Double.valueOf(-4.0 + 3.0 * 2.0),calculadora.operar(intermediario.getOperandos(),intermediario.getNumeros()));
	}

	@Test
	public void calculoCombinado2Test() {
		intermediario.agregar("3.0x-5.0+10.0/2.0+1.0/2.0");	
		assertEquals(Double.valueOf(3.0 * -5.0 + 10.0 / 2.0 + 1.0 / 2.0),calculadora.operar(intermediario.getOperandos(),intermediario.getNumeros()));
	}

	@Test
	public void mismoNumero() {
		intermediario.agregar("-2.0");
	    assertEquals(Double.valueOf(-2.0),calculadora.operar(intermediario.getOperandos(),intermediario.getNumeros()));

	}
}
