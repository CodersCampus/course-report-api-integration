package com.coderscampus.coursereportapiintegration.jobs;

import com.coderscampus.coursereportapiintegration.service.CourseReportService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CourseReportScheduler {
    private CourseReportService courseReportService;

    public CourseReportScheduler(CourseReportService courseReportService) {
        this.courseReportService = courseReportService;
    }

    @Scheduled(fixedRateString = "${api.pingRate}")
    public void pollCourseReportApi () {
        courseReportService.pollCourseReportApi();
    }


}
