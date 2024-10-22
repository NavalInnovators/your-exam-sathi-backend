package com.naval_innovators.your_exam_sathi.auth_service.config;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.naval_innovators.your_exam_sathi.auth_service.service.implementation.RedisService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;

@Component

public class JwtUtil {
	private String secret = "this_is_a_sample_secret_key";
	private long accessExpiration = 1000 * 60 * 60 * 1; // 1 hours
	private long refreshExpiration = 1000 * 60 * 60 * 24 * 7; // 7 day expiration

	public String createAccessToken(String email) {
		return generateToken(email, accessExpiration);
	}

	public String createRefreshToken(String email) {
		return generateToken(email, refreshExpiration);
	}

	public String generateToken(String email, long expiration) {
		Map<String, Object> claims = new HashMap<>();
		return Jwts.builder().setClaims(claims).setSubject(email).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(SignatureAlgorithm.HS512, secret).compact();

	}

	public Cookie setHttpCookie(String refreshToken) {
		Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
		refreshTokenCookie.setHttpOnly(true);
		refreshTokenCookie.setPath("/api/auth/refresh");
		refreshTokenCookie.setMaxAge((int)refreshExpiration);// 7 days
		return refreshTokenCookie;
	}

	public String extractUsername(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateToken(String token, String username) {
		String extractedUsername = extractUsername(token);
		return (extractedUsername.equals(username) && !isTokenExpired(token));
	}

	private boolean isTokenExpired(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getExpiration().before(new Date());
	}

}
