package com.mx.lamarrulla.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

import org.json.JSONObject;

import com.mx.lamarrulla.implement.implementAPI;

@Path("TablaAmoritza")
@Consumes("application/json")
@Produces("application/json")
public class TablaAmortiza {
	implementAPI objAPI = new implementAPI();
	JSONObject jso; // = new JSONObject(); 
	public TablaAmortiza() {}
	@GET
	public String Tabla(@Context HttpHeaders headers) {
		try {			
			objAPI.setConsulta("select * from fntablaamortcs(" + headers.getHeaderString("monto") + ", " + headers.getHeaderString("tasa") + ", " + headers.getHeaderString("plazo") + ", " + headers.getHeaderString("tipoTasa") + ");");
			objAPI.ejecutaAPI();
			jso = new JSONObject(objAPI.getstJS());//objAPI.getJsonObject();
			System.out.println(jso.toString());
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}						
		return jso.toString();
	}	
}