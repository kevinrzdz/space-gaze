package com.space_gaze.backend.security;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class TokenUtils {

	private static final Long ACCESS_TOKEN_VALIDITY_SECONDS = 2_592_000L;

	private static String getAccessTokenSecret() {
		String secret = System.getenv("JWT_SECRET");
		if (secret == null || secret.isBlank()) {
			secret = System.getProperty("jwt.secret", "74dec2d4102642138e5dc78d97f8a96c");
		}
		return secret;
	}

	public static String createToken(String name, String email, Integer id) {
		long expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS * 1000;
		Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);

		Map<String, Object> extra = new HashMap<>();
		extra.put("name", name);
		extra.put("id", id);

		return Jwts.builder()
				.setSubject(email)
				.setExpiration(expirationDate)
				.addClaims(extra)
				.signWith(Keys.hmacShaKeyFor(getAccessTokenSecret().getBytes()))
				.compact();
	}

	public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
		try {
			Claims claims = Jwts.parserBuilder()
					.setSigningKey(getAccessTokenSecret().getBytes())
					.build()
					.parseClaimsJws(token)
					.getBody();

			String email = claims.getSubject();

			return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
		} catch (JwtException e) {
			return null;
		}
	}
}
