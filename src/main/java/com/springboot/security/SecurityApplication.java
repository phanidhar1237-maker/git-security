package com.springboot.security;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.springboot.security.entity.Role;
import com.springboot.security.entity.Users;
import com.springboot.security.repository.UserDetailsRepository;

@SpringBootApplication
public class SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}
	
	  
	@Bean
    public CommandLineRunner createAdminUser(UserDetailsRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                Users admin = new Users();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin1234")); // Securely store password
                admin.setRole(Role.ADMIN);

                userRepository.save(admin);
                System.out.println("Default admin user created!");
            }
            if (userRepository.findByUsername("user").isEmpty()) {
                Users admin = new Users();
                admin.setUsername("user");
                admin.setPassword(passwordEncoder.encode("user1234")); // Securely store password
                admin.setRole(Role.USER);

                userRepository.save(admin);
                System.out.println("Default admin user created!");
            }
        };
    }
}
