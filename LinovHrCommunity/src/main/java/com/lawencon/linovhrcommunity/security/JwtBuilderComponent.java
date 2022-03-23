package com.lawencon.linovhrcommunity.security;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtBuilderComponent {
	private SecretKey secretKey;
	
	public JwtBuilderComponent() {
		secretKey = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);
	}
	
	public String generateToken(Duration duration, String id) {
		LocalDateTime expireDate = LocalDateTime.now().plusSeconds(duration.getSeconds());
		JwtBuilder jwtBuilder = Jwts.builder().signWith(secretKey).setExpiration(Timestamp.valueOf(expireDate)).setIssuer(String.valueOf(id));
		
		return jwtBuilder.compact();
	}
	
	public String getClaimId(String token) {
		String result = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().getIssuer();
		return result;
	}
}
