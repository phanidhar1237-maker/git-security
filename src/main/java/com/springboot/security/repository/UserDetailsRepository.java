package com.springboot.security.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.security.entity.Users;

@Repository
public interface UserDetailsRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByUsername(String username);
}

