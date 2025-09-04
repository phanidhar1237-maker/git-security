package com.springboot.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.security.controller.util.JWTUtil;
import com.springboot.security.entity.AuthRequest;
import com.springboot.security.entity.Users;
@RestController
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JWTUtil jwtUtil;
    @PostMapping("/authenticate")
    public ResponseEntity<String> generateToken(@RequestBody AuthRequest authRequest){
        try {
        	Authentication authentication= authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                    					authRequest.getUsername(), 
                    					authRequest.getPassword()));
        	
        	return ResponseEntity.ok(jwtUtil.generateToken(authRequest.getUsername())) ;
        	/*
        	Users users = (Users) authentication.getPrincipal();

            String token = jwtUtil.generateToken(users);
            return ResponseEntity.ok(token);  
            */

        }catch (Exception e){
        	return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        	//return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
             
        }
    }
}
