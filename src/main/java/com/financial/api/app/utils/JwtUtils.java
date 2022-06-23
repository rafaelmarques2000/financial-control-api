package com.financial.api.app.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.info.InfoProperties;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {

    @Value("${jwt_secret}")
    private String jwtSecret;

    @Value("${jwt_expiration}")
    private Integer jwtExpiration;

    public String generateJwtToken(String userId) {

        Claims claims = Jwts.claims();
        claims
                .setSubject(userId)
                .setIssuedAt(new Date());

        claims.put("role", "USER");
        Date now = new Date();
        return Jwts.builder()
                 .setClaims(claims)
                 .setExpiration(new Date(now.getTime() + (jwtExpiration*60*1000)))
                 .signWith(SignatureAlgorithm.HS512, jwtSecret)
                 .compact();
    }

    public String getUserIdFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(this.jwtSecret)
                .parseClaimsJws(token).getBody().getSubject();
    }

    public boolean isExpired(String token) {
        return Jwts.parser()
                .setSigningKey(this.jwtSecret)
                .parseClaimsJws(token).getBody().getExpiration().before(new Date());
    }

    public Jws<Claims> getAllClaims(String jwtToken) {
          return Jwts.parser()
                  .setSigningKey(this.jwtSecret)
                  .parseClaimsJws(jwtToken);
    }

}
