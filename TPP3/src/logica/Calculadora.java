package logica;

import java.util.ArrayList;
import java.util.List;

public class Calculadora {
	private Double resultado;
	private List<Double> numeros;
	private List<Character> operandos;

	public Calculadora() {
		resultado = 0.0;
		numeros = new ArrayList<Double>();
		operandos = new ArrayList<Character>();
	}

	// Si la operacion es valida devuelve el string del resultado, en caso
	// contrario devuelve SyntaxError
	public Double operar(List<Character> o, List<Double> n) {
		setNumeros(n);
		setOperandos(o);
		multiplicarYDividir();
		sumarYRestar();
		return resultado;
	}

	// Realiza las multiplicaiones y divisiones
	// Almacena los resultados en el lugar del numero posterior al operando
	// Elimina el lugar anterior y el operando
	private void multiplicarYDividir() {
		int indice_operando = 0;
		int indice_numero = 0;
		while (indice_operando < operandos.size()) {
			if (operandos.size() == numeros.size())
				indice_numero = indice_operando - 1;
			else
				indice_numero = indice_operando;
			if (operandos.get(indice_operando) == 'x') {
				multiplicarYReemplazar(indice_numero);
				numeros.remove(indice_numero);
				operandos.remove(indice_operando);
				if (indice_operando != 0)
					indice_operando--;
			} else if (operandos.get(indice_operando) == '/') {
				dividirYReemplazar(indice_numero);
				numeros.remove(indice_numero);
				operandos.remove(indice_operando);
				if (indice_operando != 0)
					indice_operando--;
			} else
				indice_operando++;
		}
	}

	//Reemplaza la lista de numeros a medida que opera
	//Elimina operandos y numeros utilizados
	private void sumarYRestar() {
		while (operandos.size() != 0) {
			if (operandos.get(0) == '+') {
				sumarYReemplazar(0);
				numeros.remove(0);
				operandos.remove(0);
			} else {
				if (operandos.size() == numeros.size()) {
					numeros.set(0, -numeros.get(0));
					operandos.remove(0);
				} else {
					restarYReemplazar(0);
					numeros.remove(0);
					operandos.remove(0);
				}
			}
		}
		resultado = numeros.get(0);
	}

	// Restriccion: no se puede dividir por 0
	private double dividir(Double a, Double b) {
		if (a == 0) {
			throw new IllegalArgumentException("El numerador no debe ser 0");
		} else if (b == 0) {
			throw new ArithmeticException("El denominador no debe ser 0");
		} else {
			return a / b;
		}
	}
	//Reemplaza numero[i+1] por la multipicacion entre numero[i] y numero[i+1]
	private void multiplicarYReemplazar(int i) {
		numeros.set(i + 1, numeros.get(i) * numeros.get(i + 1));
	}
	//Reemplaza numero[i+1] por la division entre numero[i] y numero[i+1]
	private void dividirYReemplazar(int i) {
		numeros.set(i + 1, dividir(numeros.get(i), numeros.get(i + 1)));
	}
	//Reemplaza numero[i+1] por la resta entre numero[i] y numero[i+1]
	private void restarYReemplazar(int i) {
		numeros.set(i + 1, numeros.get(i) - numeros.get(i + 1));
	}
	//Reemplaza numero[i+1] por la suma entre numero[i] y numero[i+1]
	private void sumarYReemplazar(int i) {
		numeros.set(i + 1, numeros.get(i) + numeros.get(i + 1));
	}

	private void setNumeros(List<Double> n) {
		numeros = n;
	}

	private void setOperandos(List<Character> o) {
		operandos = o;
	}
}