package com.example.sqs.config.sqs;

import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {
    @SqsListener(value = "scjpQueue")
    public void consumer(String message) {
        System.out.println("Message received from SQS: " + message);
    }
}
