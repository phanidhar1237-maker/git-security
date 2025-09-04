package com.springboot.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class PublicAccessController {
	
	@GetMapping("/health")
    public ResponseEntity<Object> createUser(@RequestParam String name) { 
    	return ResponseEntity.status(HttpStatus.OK).body(name);
    } 
}
