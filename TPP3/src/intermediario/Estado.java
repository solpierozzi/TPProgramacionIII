package intermediario;

import java.io.Serializable;
import java.util.ArrayList;

public class Estado implements Serializable {
	private static final long serialVersionUID = 1L;
	private String estadoOps;

	
	public Estado() {
		this.estadoOps="";	
	}
	
	/**
	 * Guarda el string pasado como parametro en la variable de instancia
	 * @param o
	 */
	public void SetEstado(String o) {
		this.estadoOps=o;	
	}

	public String getEstadoNums() {
		return this.estadoOps;	
	}
	
}
