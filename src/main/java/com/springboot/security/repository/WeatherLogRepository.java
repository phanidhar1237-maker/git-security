package com.springboot.security.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.security.entity.WeatherLog;

public interface WeatherLogRepository extends JpaRepository<WeatherLog, Long> {
}
