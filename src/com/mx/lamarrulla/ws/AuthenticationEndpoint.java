package com.mx.lamarrulla.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class AuthenticationEndpoint {
	@POST
	@Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
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
		return null;
	}

	private void authenticate(String username, String password) {
		// TODO Auto-generated method stub
		
	}
}
