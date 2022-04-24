package ru.inside.demo.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.inside.demo.dto.JwtTokenDto;
import ru.inside.demo.entity.User;

import java.util.Date;

@Component
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.lifetime}")
    private Integer tokenLifetimeSec;

    public String generateToken(User user){
        Date now = new Date();
        Date exp = new Date(System.currentTimeMillis() + (1000L * tokenLifetimeSec));

        return Jwts.builder()
                .claim("name", user.getName())
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public JwtTokenDto creteToken(User user){
        Date now = new Date();
        Date exp = new Date(System.currentTimeMillis() + (1000L * tokenLifetimeSec));

        String jwt = Jwts.builder()
                .claim("name", user.getName())
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();

        return new JwtTokenDto(jwt);
    }

    public Claims extractClaims(String token) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isValidToken(String token){
        try {
            return extractClaims(token).getExpiration().after(new Date());
        } catch (RuntimeException e){
            return false;
        }
    }

    public String extractName(String token){
        return extractClaims(token).get("name", String.class);
    }

}
