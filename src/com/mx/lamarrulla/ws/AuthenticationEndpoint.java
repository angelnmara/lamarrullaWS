package com.mx.lamarrulla.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mx.lamarrulla.security.Token;
import com.mx.lamarrulla.security.ProtectUserPassword;

@Path("Authentication")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public class AuthenticationEndpoint {
	
	Token token = new Token();	
	
	@POST	
	public Response authenticateUser(@FormParam("username") String username, 
            @FormParam("password") String password) {
	
	try {
	
	// Authenticate the user using the credentials provided
	authenticate(username, password);
	
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
		String Token = token.CreateToken();
		return Token;
	}

	@SuppressWarnings("static-access")
	private void authenticate(String username, String password) {
		// TODO Auto-generated method stub
		String[] strA = null;
		ProtectUserPassword protectedUser = new ProtectUserPassword();
		protectedUser.main(strA);
	}
}
