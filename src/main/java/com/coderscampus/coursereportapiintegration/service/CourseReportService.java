package com.coderscampus.coursereportapiintegration.service;

import com.coderscampus.coursereportapiintegration.dto.MatchDto;
import com.coderscampus.coursereportapiintegration.dto.response.CourseReportApiResponse;
import com.coderscampus.coursereportapiintegration.entity.Match;
import com.coderscampus.coursereportapiintegration.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.Optional;

@Service
public class CourseReportService {

    private MatchRepository matchRepository;
    private SlackBot slackBot;
    @Value("${api.url}")
    private final String COURSE_REPORT_API_URL = null;

    @Value("${api.key}")
    private final String COURSE_REPORT_API_KEY = null;

    public CourseReportService(MatchRepository matchRepository, SlackBot slackBot) {
        this.matchRepository = matchRepository;
        this.slackBot = slackBot;
    }

    public void pollCourseReportApi () {
        System.out.println("Polling Course Report API: " + LocalDateTime.now());
        RestTemplate rt = new RestTemplate();

        LocalDate now = LocalDate.now();
        LocalDate twoDaysAgo = now.minusDays(2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateEnd = formatter.format(now);
        String dateStart = formatter.format(twoDaysAgo);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", COURSE_REPORT_API_KEY);
        headers.set("Accept", "application/vnd.coursereport.v2");

        HttpEntity<String> entity = new HttpEntity<>(headers);
        String url = COURSE_REPORT_API_URL+"?page=1&per_page=1000&date_start="+dateStart+"&date_end="+dateEnd;
        System.out.println("About to call the course report api via: " + url);
        ResponseEntity<CourseReportApiResponse> response = rt.exchange(url, HttpMethod.GET, entity, CourseReportApiResponse.class);

        CourseReportApiResponse data = response.getBody();
        System.out.println("Received response: " + data);
        if (data != null) {
            // 1. iterate through response
            for (MatchDto apiMatch : data.matches()) {
                String m = """
                            Full name: %s \s
                            Email Address: %s\s
                            Phone Number: %s""";
                final String message = String.format(m, apiMatch.fullName(), apiMatch.email(), apiMatch.phoneNumber());
                // 2. check to see if this "lead" already exists
                Optional<Match> dbMatchOpt = matchRepository.findByEmail(apiMatch.email());
                dbMatchOpt.ifPresentOrElse(dbMatch -> {
                    // 2a. if "lead" exists, and the created date is within the range of the querying date then ignore it, otherwise post a "modified" message to slack
                    if (!dbMatch.getCreatedAt().equals(apiMatch.createdAt())) {
                        System.out.println("We should send a message to slack for match: " + dbMatch);
                        dbMatch.setCreatedAt(apiMatch.createdAt());
                        matchRepository.save(dbMatch);
//                        slackBot.postMessage("Lead re-opted in: \n"+message);
                    }

                }, () -> {
                    // 2b. if "lead" doesn't exist, then persist it to DB and post a message to slack
                    System.out.println("No apiMatch found");
                    Match persistedMatch = new Match(apiMatch.email(), apiMatch.createdAt(), apiMatch.fullName(), apiMatch.phoneNumber());
                    matchRepository.save(persistedMatch);

//                    slackBot.postMessage(message);
                });


            }
        }




    }
}
