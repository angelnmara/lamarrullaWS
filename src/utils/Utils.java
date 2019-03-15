package utils;

import org.json.JSONObject;
import com.mx.lamarrulla.implement.implementAPI;

public class Utils {
	
	implementAPI objAPI = new implementAPI();	
		
	public JSONObject getJso() {
		return jso;
	}

	public void setJso(JSONObject jso) {
		this.jso = jso;
	}

	public String getTabla() {
		return tabla;
	}

	public void setTabla(String tabla) {
		this.tabla = tabla;
	}

	public int getIdTabla() {
		return idTabla;
	}

	public void setIdTabla(int idTabla) {
		this.idTabla = idTabla;
	}

	public String getCampos() {
		return campos;
	}

	public void setCampos(String campos) {
		this.campos = campos;
	}

	public String getValores() {
		return valores;
	}

	public void setValores(String valores) {
		this.valores = valores;
	}	

	public int getIdTipoPeticion() {
		return idTipoPeticion;
	}

	public void setIdTipoPeticion(int idTipoPeticion) {
		this.idTipoPeticion = idTipoPeticion;
	}

	private String tabla;
	private int idTabla;
	private String campos;
	private String valores;
	/*
		1.- Get
		2.- Post
		3.- Put
		4.- Delete
	*/
	private int idTipoPeticion;	
	private JSONObject jso = new JSONObject();

	public void ejecutaConsultaAPI() {
		try {
			objAPI.setConsulta("select * from fnAPI(" + idTipoPeticion + ", '"+ tabla + "', '" + campos + "', '" + valores + "', " + idTabla + ");");
			objAPI.ejecutaAPI();			
			System.out.println(objAPI.getstJS());
			jso = new JSONObject(objAPI.getstJS());
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}		
	}	
}