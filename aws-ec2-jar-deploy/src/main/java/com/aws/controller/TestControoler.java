package com.aws.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestControoler {

    @GetMapping("/test")
    public String getTest() {
        return "Apk deploy successful";
    }
}
