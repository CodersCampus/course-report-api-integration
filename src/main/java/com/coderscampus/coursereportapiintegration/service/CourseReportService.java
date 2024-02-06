package com.coderscampus.coursereportapiintegration.service;

import com.coderscampus.coursereportapiintegration.dto.MatchDto;
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
        CourseReportApiResponse data = response.getBody();

        if (data != null) {
            // 1. iterate through response
            for (MatchDto match : data.matches()) {
                // 2. check to see if this "lead" already exists
                // 2a. if "lead" exists, and the created date is within the range of the querying date then ignore it, otherwise post a "modified" message to slack
                // 2b. if "lead" doesn't exist, then persist it to DB and post a message to slack
            }
        }




    }
}
