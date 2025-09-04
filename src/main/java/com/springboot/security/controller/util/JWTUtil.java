package com.springboot.security.controller.util;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTUtil {
    private final String SECRET = "my-super-secret-key-that-is-long-enough-1234567890!@#";
    private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());
    
    private final long EXPIRATION_TIME = 1000*60*60; //1hour
    
    
    public String generateToken(String username){
       return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
    private Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        
        /*
         * Claims nothing but payload in token
         
         * Claims will have Payload details as mention below
         
         {
			  "sub": "sunil",         // subject = username
			  "iat": 1693222222,      // issued at
			  "exp": 1693225822,      // expiration
			  "role": "ADMIN",        // custom claim 
		 }

         */
    }
    public boolean validateToken(String username, UserDetails userDetails,String token) {
       return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }
    public String extractUsername(String token){
        return extractClaims(token).getSubject();
    }
    
    
    
    
    
    
    
    /*
    public List<String> extractRoles(String token) {
        return extractClaims(token).get("roles", List.class);
    }
    */
    /*
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", userDetails.getAuthorities().stream()
                                       .map(GrantedAuthority::getAuthority)
                                       .collect(Collectors.toList()));

        return Jwts.builder()
               // .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
 
*/
}
    

