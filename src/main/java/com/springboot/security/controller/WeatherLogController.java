package com.springboot.security.controller;

import java.security.Principal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.security.entity.WeatherLog;
import com.springboot.security.service.WeatherLogService;

@RestController
@RequestMapping("/logs")
public class WeatherLogController {

    @Autowired
    private WeatherLogService weatherLogService;

    @GetMapping("/{id}")
    public WeatherLog getLog(@PathVariable Long id) {
        return weatherLogService.getLogById(id);
    }

    @PostMapping
    public WeatherLog createLog(@RequestBody WeatherLog log, Principal principal) {
        log.setPerformedBy(principal.getName());
        log.setTimestamp(LocalDateTime.now());
        return weatherLogService.createLog(log);
    }

}
