package com.auth0.javajwtsample;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import com.auth0.jwk.Jwk;
import com.auth0.jwk.JwkException;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.UrlJwkProvider;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	String token = "YOUR_JWT_TOKEN";
    	JwkProvider provider = new UrlJwkProvider("https://YOUR_TENANT.auth0.com/"); 
    	try {
    		DecodedJWT jwt = JWT.decode(token);
    		// Get the kid from received JWT token
			Jwk jwk = provider.get(jwt.getKeyId());
			
			
    	    Algorithm algorithm = Algorithm.RSA256((RSAPublicKey) jwk.getPublicKey(), null);
    	    
    	    
    	    JWTVerifier verifier = JWT.require(algorithm)
    	        .withIssuer("https://YOUR_TENANT.auth0.com/")
    	        .build();
    	    
    	    jwt = verifier.verify(token);
    	
    	} catch (JWTVerificationException e){
    	    //Invalid signature/claims
			e.printStackTrace();
    	} catch (JwkException e) {
			// invalid JWT token
			e.printStackTrace();
		}
    }
    
}
