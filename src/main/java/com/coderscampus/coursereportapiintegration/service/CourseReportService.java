package com.coderscampus.coursereportapiintegration.service;

import com.coderscampus.coursereportapiintegration.dto.MatchDto;
import com.coderscampus.coursereportapiintegration.dto.response.CourseReportApiResponse;
import com.coderscampus.coursereportapiintegration.entity.Match;
import com.coderscampus.coursereportapiintegration.repository.MatchRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CourseReportService {

    private MatchRepository matchRepository;

    public CourseReportService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public void pollCourseReportApi () {
        System.out.println("Polling Course Report API: " + LocalDateTime.now());
        RestTemplate rt = new RestTemplate();

        ResponseEntity<CourseReportApiResponse> response = rt.getForEntity("http://localhost:8080/mock-api", CourseReportApiResponse.class);
        CourseReportApiResponse data = response.getBody();

        if (data != null) {
            // 1. iterate through response
            for (MatchDto apiMatch : data.matches()) {
                // 2. check to see if this "lead" already exists
                Optional<Match> dbMatchOpt = matchRepository.findByEmail(apiMatch.email());
                dbMatchOpt.ifPresentOrElse(dbMatch -> {
                    // 2a. if "lead" exists, and the created date is within the range of the querying date then ignore it, otherwise post a "modified" message to slack
                    if (!dbMatch.getCreatedAt().equals(apiMatch.createdAt())) {
                        System.out.println("We should send a message to slack for match: " + dbMatch);
                        dbMatch.setCreatedAt(apiMatch.createdAt());
                        matchRepository.save(dbMatch);
                    }

                }, () -> {
                    // 2b. if "lead" doesn't exist, then persist it to DB and post a message to slack
                    System.out.println("No apiMatch found");
                    Match persistedMatch = new Match(apiMatch.email(), apiMatch.createdAt(), apiMatch.fullName(), apiMatch.phoneNumber());
                    matchRepository.save(persistedMatch);
                });


            }
        }




    }
}
