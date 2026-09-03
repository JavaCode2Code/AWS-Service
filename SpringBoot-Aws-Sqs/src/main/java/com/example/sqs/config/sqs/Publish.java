package com.example.sqs.config.sqs;

import io.awspring.cloud.sqs.operations.SqsTemplate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "SQS Publish API", description = "API for publishing messages to SQS")
public class Publish {

    @Autowired
    @Qualifier("publish")
    private SqsTemplate sqs;
    @Value("${sqs.url}")
    private String sqsUrl;

    @Operation(summary = "Publish a message to SQS", description = "Publishes a message to the specified SQS queue")
    @PostMapping("/publish/{msg}")
    public ResponseEntity<String> publishMessage(@PathVariable("msg") String message) {
        sqs.send(sqsUrl, message);
        return ResponseEntity.ok("Message published to SQS");
    }
}
