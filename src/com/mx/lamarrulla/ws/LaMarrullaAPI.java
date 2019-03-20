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
import javax.ws.rs.core.MediaType;

import org.json.JSONException;
import org.json.JSONObject;

import com.mx.lamarrulla.implement.Secured;
import com.mx.lamarrulla.implement.implementAPI;

import utils.Utils;

@Path("/lamarrullaAPI")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class LaMarrullaAPI {
	Utils utils = new Utils();
	implementAPI objAPI = new implementAPI();
	JSONObject jso;
	StringBuilder crunchifyBuilder = new StringBuilder();
	String campos = "";
	String valores = "";

	public LaMarrullaAPI() {}

	@GET
	@Secured
	@Path("{tabla}/{idTabla}")
	public String apiSelect(@PathParam("tabla") String tabla, @PathParam("idTabla") int idTabla) throws JSONException {		
		try {			
			System.out.println(tabla + " " + idTabla);
			utils.setIdTipoPeticion(1);
			utils.setTabla(tabla);
			utils.setIdTabla(idTabla);
			utils.ejecutaConsultaAPI();
			jso = utils.getJso();
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			jso = new JSONObject("{error:\"" + ex.getMessage() + "\"}");
		}						
		return jso.toString();
	}	
	@DELETE
	@Path("{tabla}/{idTabla}")	
	public String apiDelete(@PathParam("tabla") String tabla, @PathParam("idTabla") int idTabla) throws JSONException {
		try {			
			if(idTabla == 0) {
				System.out.println("no se procesara idtabla = " + idTabla);
				jso = new JSONObject("{error:\"Se tiene que seleccionar el identificador a borrar\"}");				
			}else {
				utils.setIdTipoPeticion(4);
				utils.setTabla(tabla);
				utils.setIdTabla(idTabla);
				utils.ejecutaConsultaAPI();
				jso = utils.getJso();
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
            utils.setIdTipoPeticion(2);
			utils.setTabla(tabla);
			utils.setCampos(campos);
			utils.setValores(valores);
			utils.ejecutaConsultaAPI();
			jso = utils.getJso();
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
			utils.setIdTipoPeticion(3);
			utils.setTabla(tabla);
			utils.setCampos(campos);
			utils.setValores(valores);
			utils.setIdTabla(idTabla);
			utils.ejecutaConsultaAPI();
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			jso = new JSONObject("{error:\"" + ex.getMessage() + "\"}");
		}						
		return jso.toString();
	}
}