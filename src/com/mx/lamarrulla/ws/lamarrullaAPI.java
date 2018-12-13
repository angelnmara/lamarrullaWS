package com.mx.lamarrulla.ws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
//import javax.ws.rs.core.Context;
//import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.json.JSONException;
import org.json.JSONObject;

import com.mx.lamarrulla.implement.Secured;
import com.mx.lamarrulla.implement.implementAPI;

@Path("lamarrullaAPI")
@Secured
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class lamarrullaAPI {
	
	implementAPI objAPI = new implementAPI();
	JSONObject jso;
	StringBuilder crunchifyBuilder = new StringBuilder();
	String campos = "";
	String valores = "";

	public lamarrullaAPI() {}

	@GET
	@Path("{tabla}/{idTabla}")
	public String apiSelect(@PathParam("tabla") String tabla, @PathParam("idTabla") String idTabla) throws JSONException {		
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
	@DELETE
	@Path("{tabla}/{idTabla}")	
	public String apiDelete(@PathParam("tabla") String tabla, @PathParam("idTabla") String idTabla) throws JSONException {
		try {			
			if(idTabla==null || idTabla == "0") {
				System.out.println("no se procesara idtabla = " + idTabla);
				jso = new JSONObject("{error:\"Se tiene que seleccionar el identificador a borrar\"}");				
			}else {
				objAPI.setConsulta("select * from fnAPI(4, '"+ tabla + "', '', '', " + idTabla + ");");
				objAPI.ejecutaAPI();
				System.out.println(objAPI.getstJS());
				jso = new JSONObject(objAPI.getstJS());
			}		
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			jso = new JSONObject("{error:\"" + ex.getMessage() + "\"}");
		}						
		return jso.toString();
	}	
	@POST
	@Path("{tabla}")
	public String apiInsert(@PathParam("tabla") String tabla, InputStream incomingData) throws JSONException {					
        try {            
            CargaCamposValores(incomingData);
            objAPI.setConsulta("select * from fnAPI(2, '"+ tabla + "', '" + campos + "', '" + valores + "', 0);");
            objAPI.ejecutaAPI();
            jso = new JSONObject(objAPI.getstJS());
        } catch (Exception e) {
            System.out.println("Error Parsing: - ");
            jso = new JSONObject("{error:\"Ocurrio un error al extraer el body\"}") ;
        }
		return jso.toString();
	}
	
	public void CargaCamposValores(InputStream incomingData) throws JSONException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
        String line = null;
        while ((line = in.readLine()) != null) {
            crunchifyBuilder.append(line);
        }
        jso = new JSONObject(crunchifyBuilder.toString());
		@SuppressWarnings("unchecked")
		Iterator<String> keys = jso.keys();
        String[] CamposA = new String[jso.length()];
        String[] ValoresA = new String[jso.length()];
        String ValorPaso = "";
        int i = 0;
        
        while(keys.hasNext()) {
            String key = keys.next();                
            if (jso.get(key) instanceof Integer) {
            	System.out.println(jso.getInt(key));
            	ValorPaso = String.valueOf(jso.getInt(key));
            }
            else if(jso.get(key) instanceof Boolean) {
            	System.out.println(jso.getBoolean(key));
            	ValorPaso = String.valueOf(jso.getBoolean(key));
            }else {
            	System.out.println(jso.getString(key));
            	ValorPaso = "''" + jso.getString(key) + "''";
            }
            CamposA[i] = key;
            ValoresA[i] = ValorPaso;
            i++;
        }
        campos = String.join(",", CamposA);
        valores = String.join(",", ValoresA);
	}
	
	@PUT
	@Path("{tabla}/{idTabla}")
	public String apiUpdate(@PathParam("tabla") String tabla, @PathParam("idTabla") int idTabla, InputStream incomingData) throws JSONException {				
		try {			
			CargaCamposValores(incomingData);
			objAPI.setConsulta("select * from fnAPI(3, '"+ tabla + "', '" + campos + "', '" + valores + "', " + idTabla + ");");
			objAPI.ejecutaAPI();			
			System.out.println(objAPI.getstJS());
			jso = new JSONObject(objAPI.getstJS());
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			jso = new JSONObject("{error:\"" + ex.getMessage() + "\"}");
		}						
		return jso.toString();
	}
}