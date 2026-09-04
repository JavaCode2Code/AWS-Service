package com.example.sns.controller;

import com.example.sns.service.SNSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SNSController {
    @Autowired
    private SNSService snsService;

    @PostMapping(path = "publish")
    public ResponseEntity<String> publishMail(@RequestBody List<String> paylod) {
        snsService.publishTopic(paylod.get(0), paylod.get(1));
        return ResponseEntity.ok("Message published to SNS topic successfully.");
    }

}
