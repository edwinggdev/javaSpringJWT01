/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sprintJWT.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 *
 * @author LENOVO
 */
public class TokenUtils {
    private final static String ACCESS_TOKEN_SECRET = "CodigoGenerado";
    private final static Long ACCESS_TOKEN_VALIDITY_SECONDS = 2_592_000L;
    
    public static String createToken(String nombre, String email){
        long expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS * 1_000;
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);
        
        Map<String, Object> extra = new HashMap();
        extra.put("nombre", nombre);
        System.out.println("creado el mapa");
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(expirationDate)
                .addClaims(extra)
                .signWith(Keys.secretKeyFor(SignatureAlgorithm.HS256))
                .compact();
    } 
    //Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes())
    public static UsernamePasswordAuthenticationToken getAuthentication(String token){
        try{
            Claims claims = Jwts.parserBuilder()
                .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
            String email = claims.getSubject();
        
        return new UsernamePasswordAuthenticationToken(email,null,Collections.emptyList());
        }catch(JwtException e){
            return null;
        }
        
    }
}
