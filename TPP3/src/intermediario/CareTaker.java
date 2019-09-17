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
	
	
	public CareTaker() {
		estAGuardar= new LinkedList<Object>();
		repo=new HistorialOperaciones();
	}
	
	private void guardarEstados() throws IOException {
		repo.agregarAMemoria(this, "Calcu.json");
	}
	
	public void setMemoria(List<Double> list, List<Character> list2) throws IOException {
		estAGuardar.add(list);
		guardarEstados();
		estAGuardar.clear();
	}
	
	public ArrayList<Double> getNumerosDMemoria(int estado) {
		return repo.getNumsDMemo();
	}
	
	public ArrayList<String> getEstadoOperandos() {
		return repo.traerOpsDMemo();
	}
	
	public ArrayList<Double> getEstadoNums(){
		return repo.getNumeros();
	}

	public void setMemoria(ArrayList<String> estadoNums) throws IOException {
		estAGuardar.add(estadoNums);
		guardarEstados();
	}
	
	public void setMemoria(String estadoNums) throws IOException {
		estAGuardar.add(estadoNums);
		guardarEstados();	
	}
	
}
