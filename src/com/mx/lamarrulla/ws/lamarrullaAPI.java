package com.mx.lamarrulla.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

import org.json.JSONObject;

import com.mx.lamarrulla.implement.implementAPI;

@Path("lamarrullaAPI")
@Consumes("application/json")
@Produces("application/json")
public class lamarrullaAPI {
	public lamarrullaAPI() {}
	@GET
	public String api(@Context HttpHeaders headers) {
		implementAPI objAPI = new implementAPI();
		objAPI.setConsulta("select * from fntablaamortcs(" + headers.getHeaderString("monto") + ", " + headers.getHeaderString("tasa") + ", " + headers.getHeaderString("plazo") + ");");
		objAPI.ejecutaAPI();
		JSONObject jso = objAPI.getJsonObject();
		String salida =  headers.getHeaderString("hola");
		System.out.println(salida);		
		//return "{\"hola\":\"" + salida + "\"}";
		return jso.toString();
	}
}
