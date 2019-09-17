package intermediario;

import java.io.Serializable;
import java.util.ArrayList;

public class Estado implements Serializable {
	private static final long serialVersionUID = 1L;
	private String estadoOps;
	private ArrayList<Integer> cantcaracteres;
	
	public Estado() {
		this.estadoOps="";
		this.cantcaracteres = new ArrayList<Integer>();
		
	}
	
	public void SetEstado(String o) {
		this.estadoOps=o;
		this.cantcaracteres.add(o.length());
	}

	public String getEstadoNums() {
		return this.estadoOps;
		
	}
	
	public ArrayList<Integer> getCantCaract(){
		return this.cantcaracteres;
	}
	
	
}
