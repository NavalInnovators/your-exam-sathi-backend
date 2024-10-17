package com.naval_innovators.your_exam_sathi.auth_service.config;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.naval_innovators.your_exam_sathi.auth_service.service.implementation.RedisService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

@Component

public class JwtUtil {
	@Autowired
	private RedisService redisService;

	private String secret = "this_is_a_sample_secret_key";
	private long expiration = 1000*60*60*1; // 1 hours
	
	public String generateToken(String email) {
		Map<String,Object> claims = new HashMap<>();
		String token = Jwts.builder()
				.setClaims(claims)
				.setSubject(email)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
		redisService.saveJwt(email, token);
		return token;
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
