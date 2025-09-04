package com.springboot.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController {
	
	//............... Role ........................
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/adminrole")
    public ResponseEntity<Object> adminRole(@RequestParam String name) { 
    	return ResponseEntity.status(HttpStatus.OK).body(name+" Admin Role Enter.");
    } 
	
	@PreAuthorize("hasRole('USER')")  
	@GetMapping("/userrole")
    public ResponseEntity<Object> userRole(@RequestParam String name) { 
		//testing for git
    	return ResponseEntity.status(HttpStatus.OK).body(name+" UserRole Enter ");
    }
	
	@PreAuthorize("hasAnyRole('ADMIN','USER')") 
	@GetMapping("/anyrole")
    public ResponseEntity<Object> anyRole(@RequestParam String name) { 
    	return ResponseEntity.status(HttpStatus.OK).body(name+" Any Role ");
    }
	
	//.....................Authority...............
	
	@PreAuthorize("hasAuthority('WEATHER_WRITE')")
	@GetMapping("/weatherwrite")
    public ResponseEntity<Object> weatherWrite(@RequestParam String name) { 
    	return ResponseEntity.status(HttpStatus.OK).body(name+" weatherWrite Enter.");
    } 
	
	@PreAuthorize("hasAuthority('WEATHER_READ')")
	@GetMapping("/weatherread")
    public ResponseEntity<Object> weatherRead(@RequestParam String name) { 
    	return ResponseEntity.status(HttpStatus.OK).body(name+" weatherRead Enter  ");
    } 

}
