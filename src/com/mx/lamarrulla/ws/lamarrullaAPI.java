package com.mx.lamarrulla.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
	@Path("{tabla}")
	public String apiSelect(@PathParam("tabla") String tabla, @PathParam("idTabla") String idTabla) throws JSONException {
		// 
		//@Context HttpHeaders headers
		//headers.getHeaderString("tabla")
		//headers.getHeaderString("tabla")
		//jso = new JSONObject("{" + tabla + " : " + objAPI.getstJS() + "}");
		try {			
			System.out.println(tabla + " " + idTabla);
			if(idTabla==null) {
				idTabla = "0";
			}
			objAPI.setConsulta("select * from fnAPI(1, '"+ tabla + "', '', '', " + idTabla + ");");
			objAPI.ejecutaAPI();			
			System.out.println(objAPI.getstJS());
			jso = new JSONObject(objAPI.getstJS());
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			jso = new JSONObject("{error:\"" + ex.getMessage() + "\"}");
		}						
		return jso.toString();
	}	
//	@DELETE
//	public String apiDelete(@PathParam("tabla") String tabla, @PathParam("idTabla") int idTabla) throws JSONException {
//		try {			
//			objAPI.setConsulta("select * from fnAPI(4, '"+ tabla + "', '', '', " + idTabla + ");");
//			objAPI.ejecutaAPI();			
//			System.out.println(objAPI.getstJS());
//		}catch(Exception ex) {
//			System.out.println(ex.getMessage());
//			jso = new JSONObject("{error:\"" + ex.getMessage() + "\"}");
//		}						
//		return jso.toString();
//	}	
//	@POST
//	public String apiInsert(@PathParam("tabla") String tabla, @Context HttpHeaders headers) throws JSONException {				
//		try {			
//			String campos = headers.getHeaderString("campos");
//			String valores = headers.getHeaderString("valores");
//			objAPI.setConsulta("select * from fnAPI(2, '"+ tabla + "', " + campos + ", " + valores + ", 0);");
//			objAPI.ejecutaAPI();			
//			System.out.println(objAPI.getstJS());
//		}catch(Exception ex) {
//			System.out.println(ex.getMessage());
//			jso = new JSONObject("{error:\"" + ex.getMessage() + "\"}");
//		}						
//		return jso.toString();
//	}
//	@PUT	
//	public String apiUpdate(@PathParam("tabla") String tabla, @PathParam("id") int idTabla, @Context HttpHeaders headers) throws JSONException {				
//		try {			
//			String campos = headers.getHeaderString("tabla");
//			String valores = headers.getHeaderString("valores");
//			objAPI.setConsulta("select * from fnAPI(3, '"+ tabla + "', " + campos + ", " + valores + ", " + idTabla + ");");
//			objAPI.ejecutaAPI();			
//			System.out.println(objAPI.getstJS());
//		}catch(Exception ex) {
//			System.out.println(ex.getMessage());
//			jso = new JSONObject("{error:\"" + ex.getMessage() + "\"}");
//		}						
//		return jso.toString();
//	}
}