package com.coderscampus.coursereportapiintegration;

import com.coderscampus.coursereportapiintegration.service.CourseReportService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CourseReportApiIntegrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseReportApiIntegrationApplication.class, args);
		CourseReportService service = new CourseReportService();
		service.pollCourseReportApi();

	}

}
