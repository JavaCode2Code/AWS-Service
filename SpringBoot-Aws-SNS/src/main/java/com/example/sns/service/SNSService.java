package com.example.sns.service;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SNSService {
    @Autowired
    private AmazonSNS amazonSNS;
    @Value("${topic.arn}")
    private String topicArn;

    public void publishTopic(String subject, String message) {
        try{
            PublishRequest request = new PublishRequest(topicArn,message);
            request.setSubject(subject);
            amazonSNS.publish(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
