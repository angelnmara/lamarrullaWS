package com.mx.lamarrulla.ws;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

import com.mx.lamarrulla.security.Token;
import com.mx.lamarrulla.security.VerifyProvidedPassword;

import utils.Utils;

import com.mx.lamarrulla.implement.implementAPI;
import com.mx.lamarrulla.security.ProtectUserPassword;

@Path("Authentication")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public class AuthenticationEndpoint {	
	
	implementAPI objAPI = new implementAPI();
	Token token = new Token();
	Utils utils = new Utils();
	JSONObject jso = new JSONObject();
	
	@POST	
	public Response authenticateUser(@FormParam("username") String username,
			@FormParam("correo") String correo,
            @FormParam("password") String password) {
	
	try {
	System.out.println(username);
	System.out.println(password);
	System.out.println(correo);
	// Authenticate the user using the credentials provided
	authenticate(username, correo, password);
	
	// Issue a token for the user
	String token = issueToken(username);
	
	// Return the token on the response
	return Response.ok(token).build();
	
		} catch (Exception e) {
			return Response.status(Response.Status.FORBIDDEN).build();
		}      
	}

	private String issueToken(String username) {
		// TODO Auto-generated method stub		
		token.setUser(username);
		String Token = token.CreateToken();
		return Token;
	}

	@SuppressWarnings("static-access")
	private void authenticate(String username, String correo, String password) throws JSONException {
		// TODO Auto-generated method stub
		//String[] strA = null;
		
//		ProtectUserPassword protectedUser = new ProtectUserPassword();
//		protectedUser.setMyPassword(password);
//		protectedUser.generaPassword();
		recuperaPassword(username, correo, password);
		VerifyProvidedPassword vpp = new VerifyProvidedPassword();
		vpp.setSecurePassword(jso.getString("fcsecpassw"));
		vpp.setSalt(jso.getString("fcsalt"));
		vpp.setProvidedPassword(password);
		if(vpp.verificaPassword()) {
			System.out.println("validado");
		}else {
			System.out.println("no validado");
		}
	}

	private void recuperaPassword(String username, String correo,  String password) throws JSONException {
		// TODO Auto-generated method stub
		String consulta = "select fcsecpassw, fcsalt\n" + 
				"from tbusupassw a\n" + 
				"inner join tbusu b\n" + 
				"on a.fiidusu = b.fiidusu\n" + 
				"where fcusupassw = crypt('" + password + "', fcusupassw)\n" + 
				"and fcusunom = '" + username + "' or fcusucorrelec = '" + correo + "'";
		utils.setConsulta(consulta);
		utils.ejecutaConsultaJSON();						
		jso = utils.getJso();
		System.out.println(jso.toString());
	}
}
