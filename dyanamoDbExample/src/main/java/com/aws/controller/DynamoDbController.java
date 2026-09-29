package com.aws.controller;

import com.aws.model.User;
import com.aws.service.DynamoDBService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DynamoDbController {


    @Autowired
    private DynamoDBService service;

    @PostMapping("/add")
    public ResponseEntity<String> addRecord(@RequestBody User user) {
        return new ResponseEntity<>(service.addRecord(user), HttpStatus.OK);
    }

    @PostMapping("/savejson/{currentId}")
    public ResponseEntity<String> saveJson(@PathVariable Integer currentId) throws JsonProcessingException {
        service.saveJson(currentId);
        return new ResponseEntity<>("Json Saved", HttpStatus.OK);
    }

    @GetMapping("agebetween/{start}/{end}")
    public ResponseEntity<List<User>> ageBetween(@PathVariable Integer start,
                                                 @PathVariable Integer end) {
        return new ResponseEntity<>(service.ageBetween(start, end), HttpStatus.OK);
    }

    @GetMapping("agebetween/with/email/{start}/{end}/{email}")
    public ResponseEntity<List<User>> ageBetweenWithEmail(@PathVariable Integer start,
                                                          @PathVariable Integer end, @PathVariable String email) {
        return new ResponseEntity<>(service.ageBetweenWithEmail(start, end, email), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody User user) {
        service.update(user);
        return new ResponseEntity<>("Record Updated", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> delete(@PathVariable String userId)
    {
        service.delete(userId);
        return new ResponseEntity<>("Record deleted",HttpStatus.OK);
    }
}
