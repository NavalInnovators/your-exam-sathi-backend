package com.naval_innovators.your_exam_sathi.auth_service.config;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	private String secret = "this_is_a_sample_secret_key";
	private long expiration = 1000*60*60*1; // 1 hours
	
	public String generateToken(String userName) {
		Map<String,Object> claims = new HashMap<>();
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(userName)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
	}
	
	 public String extractUsername(String token) {
	        return Jwts.parser()
	                .setSigningKey(secret)
	                .parseClaimsJws(token)
	                .getBody()
	                .getSubject();
	 }
	 
	 public boolean validateToken(String token, String username) {
	        String extractedUsername = extractUsername(token);
	        return (extractedUsername.equals(username) && !isTokenExpired(token));
	    }
	 private boolean isTokenExpired(String token) {
	        return Jwts.parser()
	                .setSigningKey(secret)
	                .parseClaimsJws(token)
	                .getBody()
	                .getExpiration()
	                .before(new Date());
	    }
	
}
