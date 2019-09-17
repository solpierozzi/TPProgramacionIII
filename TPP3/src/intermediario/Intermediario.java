package intermediario;

import java.util.ArrayList;
import java.util.List;

import logica.Calculadora;


public class Intermediario {
	private ArrayList<Character> operandos;
	private ArrayList<Double> numeros;
	private Calculadora calculadora;
	

	public Intermediario() {
		operandos = new ArrayList<Character>();
		numeros = new ArrayList<Double>();
		calculadora= new Calculadora();
	}

	// Almacena los operandos en la lista operandos y los numeros en la lista numeros
	public void agregar(String s){
		reiniciarValores();
		int ultimoOperando = 0, operandoAnterior = 0;
		for (int i = 1; i < s.length(); i++) {
			if (esOperando(s.charAt(i))) {
				if (esOperando(s.charAt(i - 1))) {
					if (numeros.isEmpty()) {
						ultimoOperando = i;
						operandos.add(s.charAt(i));
					} 
					else
						operandoAnterior++;
				} 
				else {
					operandoAnterior = ultimoOperando;
					ultimoOperando = i;
					operandos.add(s.charAt(i));
					if (numeros.isEmpty())
						numeros.add(Double.parseDouble(s.substring(operandoAnterior, ultimoOperando)));
					else
						numeros.add(Double.parseDouble(s.substring(operandoAnterior + 1, ultimoOperando)));
				}
			}
		}
		if (numeros.isEmpty())
			numeros.add(Double.parseDouble(s.substring(ultimoOperando,s.length())));
		else
			numeros.add(Double.parseDouble(s.substring(ultimoOperando + 1,s.length())));
	}
	public String resultadoOperacion(){
		return calculadora.operar(operandos, numeros).toString();
	}
	public void reiniciarValores() {
		numeros.clear();
		operandos.clear();
	}

	public List<Character> getOperandos() {
		return operandos;
	}
	public ArrayList<Character> getOperandosArray() {
		return operandos;
	}

	public List<Double> getNumeros() {
		return numeros;
	}
	public ArrayList<Double> getNumerosArray() {
		return numeros;
	}

	private boolean esOperando(char s) {
		return s == 'x' || s == '/' || s == '+' || s == '-';
	}

	protected void setOperandos(ArrayList<Character> c) {
		this.operandos=c;
		
	}
	protected void setNumeros(ArrayList<Double> d) {
		this.numeros = d;
	}
	
}
