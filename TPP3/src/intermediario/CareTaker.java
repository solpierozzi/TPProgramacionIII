package intermediario;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import memoria.HistorialOperaciones;

public class CareTaker implements Serializable {
	private static final long serialVersionUID = 1L;
	private LinkedList<Object> estAGuardar;
	private HistorialOperaciones repo;
	
	/**
	 * Constructor, inicializa una lista para ir guardando los string de Estado
	 */
	public CareTaker() {
		estAGuardar= new LinkedList<Object>();
		repo=new HistorialOperaciones();
	}
	
	private void guardarEstados() throws IOException {
		repo.agregarAMemoria(this, "calcuSan.json");
	}
	/**
	 * 
	 Metodo cual agrega a la variable de instancia dos listas, luego utiliza la funcion guardarEstados para 
	 llamar a HistorialDeOperaciones y guardar la lista en el archivo. luego limpia la variable de instancia
	 	 */
	public void setMemoria(List<Double> list, List<Character> list2) throws IOException {
		estAGuardar.add(list);
		guardarEstados();
		estAGuardar.clear();
	}
	
	public ArrayList<Double> getNumerosDMemoria(int estado) {
		return repo.getNumsDMemo();
	}
	/**
	 * llamando a HistorialDeAplicaciones devuelve un array con todos los valores de tipo String.
	 */
	public ArrayList<String> getEstadoOperandos() {
		return repo.traerOpsDMemo();
	}
	
	public ArrayList<Double> getEstadoNums(){
		return repo.getNumeros();
	}

	/**
	 * 
		Guarda en la variable de instancia (estAGuardar) la lista de tipo String y luego llama a guardarEstados para
		enviarla al archivo 
	 */
	public void setMemoria(ArrayList<String> estadoNums) throws IOException {
		estAGuardar.add(estadoNums);
		guardarEstados();
	}
	/**
	Guarda en la variable de instancia (estAGuardar) la lista de tipo String y luego llama a guardarEstados para
	enviarla al archivo
	**/ 
	public void setMemoria(String estadoNums) throws IOException {
		estAGuardar.add(estadoNums);
		guardarEstados();	
	}
	
}
