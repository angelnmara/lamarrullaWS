package com.mx.lamarrulla.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.mx.lamarrulla.implement.implementAPI;
import com.mx.lamarrulla.security.ProtectUserPassword;

@Path("AltaUsuario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public class AltaUsuario {
	
	implementAPI objAPI = new implementAPI();
	int idUsuario = 0;
	int idPassw = 0;
	String error = "{\"Error\":\"%s\"}";
	String salida = "{\"respuesta\":\"usuario dado de alta satisfactoriamente\"}";
	
	@POST 
	public String altaUsuario(
			@FormParam("username") String username,
			@FormParam("correo") String correo,
            @FormParam("password") String password) {
		System.out.println(username);
		System.out.println(correo);
		System.out.println(password);
					
		if(!insertaUsuario(username, correo)) {						
			return salida;
		}
		
		ProtectUserPassword protectedUser = new ProtectUserPassword();
		protectedUser.setMyPassword(password);
		protectedUser.generaPassword();
		
		if(!insertaPassword(username, password)) {			
			return salida;
		}
						
		return salida;		
	}

	private boolean insertaPassword(String username, String password) {
		// TODO Auto-generated method stub
		boolean respuesta = false;
		try {
			ProtectUserPassword pup = new ProtectUserPassword();
			pup.setMyPassword(password);
			pup.generaPassword();
			String consulta = "insert into tbUsuPassw(fiIdUsu, fcUsuPassw, fcSecPassw, fcSalt)\r\n" + 
					"values(" + idUsuario + ", crypt('" + password + "', gen_salt('bf')), '" + pup.getMySecurePassword() + "', '" + pup.getSalt() + "') returning fiIdUsuPassw;";
			objAPI.setConsulta(consulta);
			objAPI.ejecutaAPI();
			String respuestaConsulta = objAPI.getstJS(); 
			if(respuestaConsulta.matches("-?\\d+")) {
				idPassw = Integer.parseInt(respuestaConsulta);
				respuesta = true;
			}else {
				salida = String.format(error, respuestaConsulta.replaceAll("\"", "'"));
				System.out.println("Ocurrio un error al insertar en tbUsuPAssw: " + respuestaConsulta);
			}					
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return respuesta;
	}

	private boolean insertaUsuario(String username, String correo) {
		// TODO Auto-generated method stub
		boolean respuesta = false;
		try {
			String consulta = "insert into tbUsu(fcUsuNom, fcUsuCorrElec, fiIdRol, fiIdEmpresa) \r\n" + 
							"values ('" + username + "', '" + correo + "', 1, 1) returning fiIdUsu;";
			System.out.println(consulta);
			objAPI.setConsulta(consulta);
			objAPI.ejecutaAPI();
			String respuestaConsulta = objAPI.getstJS(); 
			if(respuestaConsulta.matches("-?\\d+")) {
				idUsuario = Integer.parseInt(respuestaConsulta);
				respuesta = true;
			}else {
				salida = String.format(error, respuestaConsulta.replaceAll("\"", "'"));
				System.out.println("Existe un error al insertar tbUsu " + respuestaConsulta);
			}
		}catch(Exception ex) {
			respuesta = false;
		}			
		return respuesta;
	} 
}