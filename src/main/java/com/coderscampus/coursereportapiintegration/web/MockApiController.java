package com.coderscampus.coursereportapiintegration.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MockApiController {

    @GetMapping("/mock-api")
    public ResponseEntity<?> fetchData () {

        return ResponseEntity.ok("OK");
    }
}
