package com.coderscampus.coursereportapiintegration.service;

import com.coderscampus.coursereportapiintegration.dto.response.CourseReportApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class CourseReportService {

    public void pollCourseReportApi () {
        System.out.println("Polling Course Report API: " + LocalDateTime.now());
        RestTemplate rt = new RestTemplate();

        ResponseEntity<CourseReportApiResponse> response = rt.getForEntity("http://localhost:8080/mock-api", CourseReportApiResponse.class);
        System.out.println(response.getBody());
    }
}
