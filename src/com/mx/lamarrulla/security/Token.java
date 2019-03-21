package com.mx.lamarrulla.security;

//import java.security.Key;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class Token {
	
	private String user;
	private String secret;
	private String token;
	private boolean autenticado = false;
		
	public boolean isAutenticado() {
		return autenticado;
	}

	public void setAutenticado(boolean autenticado) {
		this.autenticado = autenticado;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void CreateToken() {		
		try {
		    Algorithm algorithm = Algorithm.HMAC256(secret);
		    token = JWT.create()
		        .withIssuer(user)
		        .sign(algorithm);
		} catch (JWTCreationException exception){
		    //Invalid Signing configuration / Couldn't convert Claims.
			System.out.println(exception.getMessage());
		}			
	}
	public void VerifyToken() {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
		    JWTVerifier verifier = JWT.require(algorithm)
		        .withIssuer(user)
		        .build(); //Reusable verifier instance
		    DecodedJWT jwt = verifier.verify(token);
		    autenticado = true;		    
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			autenticado = false;
		}		
	}
}