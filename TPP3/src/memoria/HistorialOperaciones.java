package memoria;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

public class HistorialOperaciones implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;
	private ArrayList<String> operandos;
	private ArrayList<Double> numeros;

	public HistorialOperaciones() {
		operandos = new ArrayList<String>();
		numeros = new ArrayList<Double>();
	}

	public void generarJSON(String archivo) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(this);

		try {
			FileWriter writer = new FileWriter(archivo);
			writer.write(json);
			writer.close();
			
		} catch (Exception e) {
			return;
		}
	}

	public void agregarAMemoria(Object o, String arch) {
		try {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String jsonString = gson.toJson(o);
			FileWriter writer = new FileWriter("calcuSAn.json");
			writer.write(jsonString);
			writer.close();
		}
		
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public ArrayList<Double> getNumsDMemo() {
		ArrayList<Double> numerosARet = new ArrayList<Double>();
		try {
			JsonParser parser = new JsonParser();
			FileReader fr = new FileReader("calcuSAn.json");
			JsonElement datos = parser.parse(fr);
			elementosSegunTipo(datos);

			for (int i = 0; i < numeros.size(); i++) {
				numerosARet.add(numeros.get(i));
			}
			return numerosARet;
		} 
		
		catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("error archivo");
		}
	}

	public ArrayList<String> traerOpsDMemo() {

		ArrayList<String> operandosARet = new ArrayList<String>();
		try {
			operandos = new ArrayList<String>();
			JsonParser parser = new JsonParser();
			FileReader fr = new FileReader("calcuSAn.json");
			JsonElement datos = parser.parse(fr);
			elementosSegunTipo(datos);
			Set<String> hashSet = new HashSet<String>(operandos);
			operandosARet.addAll(hashSet);
			return operandosARet;
		} 
		
		catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("error archivo");
		}
	}

	public ArrayList<Double> traerNumsDMemo() {
		try {
			JsonParser parser = new JsonParser();
			FileReader fr = new FileReader("calcuSAn.json");
			JsonElement datos1 = parser.parse(fr);
			elementosSegunTipo(datos1);
			return null;
		} 
		
		catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("error archivo");
		}
	}

	public void eliminarMemoria(String arch) {
		try {
			FileOutputStream fos = new FileOutputStream(arch);
			ObjectOutputStream out = new ObjectOutputStream(fos);
			out.writeObject(null);
			out.close();
		}
		
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void agregarAMemoria(LinkedList<Object> o, String arch) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonString = gson.toJson(o);
		FileWriter writer = new FileWriter("calcuSAn.json");
		writer.write(jsonString);
		writer.close();
	}

	public void elementosSegunTipo(JsonElement elemento) {
		if (elemento.isJsonObject()) {
			JsonObject obj = elemento.getAsJsonObject();
			java.util.Set<java.util.Map.Entry<String, JsonElement>> entradas = obj.entrySet();
			java.util.Iterator<java.util.Map.Entry<String, JsonElement>> iter = entradas.iterator();
			while (iter.hasNext()) {
				java.util.Map.Entry<String, JsonElement> entrada = iter.next();
				elementosSegunTipo(entrada.getValue());
			}
		} 
		else if (elemento.isJsonArray()) {
			JsonArray array = elemento.getAsJsonArray();
			java.util.Iterator<JsonElement> iter = array.iterator();
			while (iter.hasNext()) {
				JsonElement entrada = iter.next();
				elementosSegunTipo(entrada);
			}
		} 
		else if (elemento.isJsonPrimitive()) {
			JsonPrimitive valor = elemento.getAsJsonPrimitive();
			if (valor.isNumber()) {
				numeros.add(valor.getAsDouble());
				return;
			} 
			else if (valor.isString()) {
				if (!valor.getAsString().equals(""))
					operandos.add(valor.getAsString());
				return;
			}
		}
	}

	public ArrayList<Double> getNumeros() {
		return numeros;
	}

	public ArrayList<String> getOperandos() {
		return operandos;
	}
}
