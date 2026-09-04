package com.example.cloud.watch.controller;

import com.example.cloud.watch.service.CloudWatchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "CloudWatch Operations API", description = "API for managing CloudWatch resources")
public class CloudWatchController {

    @Autowired
    private CloudWatchService service;

    @Operation(summary = "Log a message to CloudWatch", description = "Logs the provided message to AWS CloudWatch")
    @PostMapping("/publish/{message}")
    public ResponseEntity<String> logMessageToCloudWatch(@PathVariable String message) {
        service.logMessageToCloudWatch(message);
        System.out.println("Message logged to CloudWatch: " + service.toString());
        return new ResponseEntity<>("Message logged to CloudWatch successfully", HttpStatus.OK);
    }
@Operation(summary = "Test the CloudWatch API", description = "Tests the CloudWatch API endpoint")
    @GetMapping("/test")
    public String test() {
        return "CloudWatch API is working!";
    }

}
