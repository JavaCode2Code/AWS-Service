package com.example.sns.controller;

import com.example.sns.service.SNSService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "SNS Publish API", description = "API for publishing messages to SNS")
public class SNSController {
    @Autowired
    private SNSService snsService;
    @Operation(summary = "Publish a message to SNS", description = "Publishes a message to the specified SNS Service")
    @PostMapping(path = "publish")
    public ResponseEntity<String> publishMail(@RequestBody List<String> paylod) {
        snsService.publishTopic(paylod.get(0), paylod.get(1));
        return new ResponseEntity<>("Message published to SNS topic successfully.",HttpStatus.OK);
    }

}
