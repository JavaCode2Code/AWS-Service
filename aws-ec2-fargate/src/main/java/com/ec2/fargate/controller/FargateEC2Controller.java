package com.ec2.fargate.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FargateEC2Controller {

   @GetMapping("/test")
   public String test(){
      return "Hello World api";
   }
}