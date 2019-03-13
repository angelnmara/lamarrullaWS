package com.mx.lamarrulla.security;

//import java.security.Key;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

public class Token {
	
	private String user;
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String CreateToken() {
		String token = "";
		try {
		    Algorithm algorithm = Algorithm.HMAC256("secret");
		    token = JWT.create()
		        .withIssuer(user)
		        .sign(algorithm);
		} catch (JWTCreationException exception){
		    //Invalid Signing configuration / Couldn't convert Claims.
			System.out.println(exception.getMessage());
		}	
		return token;
	}
}
