package com.mx.lamarrulla.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

import org.json.JSONException;
import org.json.JSONObject;

import com.mx.lamarrulla.implement.Secured;
import com.mx.lamarrulla.implement.implementAPI;

@Path("lamarrullaAPI")
@Secured
@Consumes("application/json")
@Produces("application/json")
public class lamarrullaAPI {
	implementAPI objAPI = new implementAPI();
	JSONObject jso; // = new JSONObject();
	public lamarrullaAPI() {}
	@GET
	public String api(@Context HttpHeaders headers) throws JSONException {
		try {			
			objAPI.setConsulta("select * from fnAPI('"+ headers.getHeaderString("tabla") + "');");
			objAPI.ejecutaAPI();		
			jso = new JSONObject("{" + headers.getHeaderString("tabla") + " : " + objAPI.getstJS() + "}");
			System.out.println(jso.toString());
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			jso = new JSONObject("{error:\"" + ex.getMessage() + "\"}");
		}						
		return jso.toString();
	}
}