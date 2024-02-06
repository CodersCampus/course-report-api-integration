package com.coderscampus.coursereportapiintegration.web;

import com.coderscampus.coursereportapiintegration.dto.MatchDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class MockApiController {

    @GetMapping("/mock-api")
    public ResponseEntity<Map<String, Object>> fetchData () {

        Map<String, Object> matches = Map.of(
                "matches", List.of(
                        new MatchDto("John Doe", "john.doe@example.com", "+1 555-0100", "Beginner", "Part Time", "Online", true, "February 4, 2024 at 9:19am UTC", "Full Stack Developer"),
                        new MatchDto("Jane Smith", "jane.smith@example.com", "+1 555-0101", "Beginner", "Part Time", "Online", true, "February 4, 2024 at 7:33am UTC", "Full Stack Developer"),
                        new MatchDto("Alex Johnson", "alex.johnson@example.com", "+1 555-0102", "Beginner", "Part Time", "Online", true, "February 4, 2024 at 2:49am UTC", "Full Stack Developer"),
                        new MatchDto("Emily Davis", "emily.davis@example.com", "+1 555-0103", "Some Experience", "Full Time", "Online", true, "February 3, 2024 at10:24pm UTC", "Full Stack Developer")
                ),
                "meta", Map.of(
                        "pagination", Map.of(
                                "current_page", "1",
                                "per_page", "25",
                                "total_pages", 1,
                                "total_objects", 4
                        )
                )
        );
        return ResponseEntity.ok(matches);
    }
}
