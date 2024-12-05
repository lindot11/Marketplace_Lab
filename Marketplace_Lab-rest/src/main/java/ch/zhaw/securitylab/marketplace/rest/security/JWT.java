package ch.zhaw.securitylab.marketplace.rest.security;

import javax.crypto.spec.SecretKeySpec;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.SignatureException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import java.time.Instant;
import java.util.Date;

public class JWT {
    private static final String ISSUER = "Marketplace";
    private static final long VALIDITY = 3600; // 1 hour
    private static final SecretKeySpec KEY = 
        new SecretKeySpec("marketplace_12345678901234567890".getBytes(), "HmacSHA256");
    
    public static String createJWT(String username) {
        String jwt = Jwts.builder()
                .setIssuer(ISSUER)
                .setSubject(username)
                .setExpiration(Date.from(Instant.now().plusSeconds(VALIDITY)))
                .signWith(KEY)
                .compact();
        return jwt;
    }
    
    public static String validateJWTandGetUsername(String jwt) {
        try {
            return Jwts.parserBuilder().setSigningKey(KEY).build().
                parseClaimsJws(jwt).getBody().getSubject();
        } catch (MalformedJwtException | SignatureException | ExpiredJwtException e) {
        }
        return null;
    }
}
