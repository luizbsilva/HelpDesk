package com.bisao.helpdesk.api.security.jwt;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.bisao.helpdesk.util.DateUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = 1804640419921856024L;
	
	static final String CLAIM_KEY_USERNAME = "sub";
	static final String CLAIM_KEY_CREATED = "created";
	static final String CLAIM_KEY_EXPIRED = "exp";

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private Long expiration;

	public String getUserNameFromToken(String token) {
		String userName = null;
		try {

			userName = this.getClaimsFromToken(token).getSubject();
		} catch (Exception e) {
			userName = null;
		}

		return userName;

	}

	public LocalDate getExpirationFromToken(String token) {
		LocalDate expiration;
		try {

			expiration = DateUtil.converteDateParaLocalDate(this.getClaimsFromToken(token).getExpiration());
		} catch (Exception e) {
			expiration = null;
		}

		return expiration;
	}

	private Claims getClaimsFromToken(String token) {
		Claims claims;
		try {

			claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			claims = null;
		}
		return claims;
	}

	private boolean isTokenExpired(String token) {
		return getExpirationFromToken(token).isBefore(DateUtil.converteDateParaLocalDate(new Date()));

	}

	public String genereteToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();

		claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
		claims.put(CLAIM_KEY_CREATED, DateUtil.converteDateParaLocalDate(new Date()));

		return doGenerateToken(claims);
	}

	private String doGenerateToken(Map<String, Object> claims) {
		final Date createDate = (Date) claims.get(CLAIM_KEY_CREATED);
		final Date expirationDate = new Date(createDate.getTime() + expiration * 1000);
		return Jwts.builder().setClaims(claims).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, secret)
				.compact();
	}

	public Boolean canTokenBeRefreshed(String token) {
		return !isTokenExpired(token);

	}

	public String refreshToken(String token) {
		String refreshedToken;
		try {
			final Claims claims = getClaimsFromToken(token);
			claims.put(CLAIM_KEY_CREATED, new Date());
			refreshedToken = doGenerateToken(claims);

		} catch (Exception e) {
			refreshedToken = null;
		}
		return refreshedToken;
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		JwtsUser user = (JwtsUser) userDetails;
		final String userName = getUserNameFromToken(token);
		return userName.equals(user.getUsername()) && !isTokenExpired(token);

	}
}
