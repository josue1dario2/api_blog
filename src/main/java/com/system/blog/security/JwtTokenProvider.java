package com.system.blog.security;

import com.system.blog.exceptions.BlogAppException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-miliseconds}")
    private int jwtExpirationInMs;

    public String generateAccessToken(Authentication authentication){
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expirationDate = new Date(currentDate.getTime() + jwtExpirationInMs);

        return  Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.ES512,jwtSecret)
                .compact();

    }
    public String getUsernameFromJwt(String token){
        Claims claims = Jwts.parser().setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        }catch (SignatureException ex){
            throw new BlogAppException(HttpStatus.BAD_REQUEST,"Invalid jwt signature");
        }catch (MalformedJwtException ex){
            throw new BlogAppException(HttpStatus.BAD_REQUEST,"Invalid jwt token");
        }catch (ExpiredJwtException ex){
            throw new BlogAppException(HttpStatus.BAD_REQUEST,"Expired jwt token");
        }catch (UnsupportedJwtException ex){
            throw new BlogAppException(HttpStatus.BAD_REQUEST,"Jwt token not supported");
        }catch (IllegalArgumentException ex){
            throw new BlogAppException(HttpStatus.BAD_REQUEST,"The jwt claims string is empty");
        }
    }
}
