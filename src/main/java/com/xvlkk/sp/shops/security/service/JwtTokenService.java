package com.xvlkk.sp.shops.security.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.xvlkk.sp.shops.security.config.JwtConfig;
import com.xvlkk.sp.shops.security.dto.UserDetailsImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtTokenService {
    private final JwtDecoder jwtDecoder;

    private final JwtConfig jwtConfig;

    public JwtTokenService(@Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}") String issuerUri, JwtConfig jwtConfig) {
        this.jwtDecoder = NimbusJwtDecoder.withJwkSetUri(issuerUri + "/.well-known/jwks.json").build();
        this.jwtConfig = jwtConfig;
    }

    /**
     * generate a JWT token.
     *
     * @return Jwt string.
     */
    public String generateToken(UserDetailsImpl user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtConfig.getSecretKey());

            return JWT.create()
                    .withIssuer(jwtConfig.getIssuerApplication())
                    .withIssuedAt(creationDate())
                    .withExpiresAt(expirationDate())
                    .withSubject(user.getUsername())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error in token generate!", exception);
        }
    }

    /**
     * Decodes and validates a JWT token.
     *
     * @param token The received JWT token.
     * @return Jwt object containing the token information.
     * @throws JwtException if the token is invalid.
     */
    public Jwt decodeToken(String token) {
        return jwtDecoder.decode(token);
    }

    /**
     * Extracts the subject (usually the username or user_id) from the JWT.
     */
    public String extractUsername(String token) {
        return decodeToken(token).getSubject();
    }

    /**
     * Extracts the subject (usually the username or user_id) from the JWT and verifies if it is valid.
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String userName = extractUsername(token);
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    /**
     * Checks if the token is expired.
     */
    public boolean isTokenExpired(String token) {
        Date expiration = extractClaim(token, Claims::getExpiration);
        return expiration.before(new Date());
    }

    /**
     * Generic method to extract claims from the token.
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extracts all claims from the JWT token.
     */
    private Claims extractAllClaims(String token) {
        Key secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtConfig.getSecretKey()));

        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Date creationDate() {
        return Date.from(ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).toInstant());
    }

    private Date expirationDate() {
        return Date.from(ZonedDateTime.now(ZoneId.of("America/Recife")).plusHours(4).toInstant());
    }
}
