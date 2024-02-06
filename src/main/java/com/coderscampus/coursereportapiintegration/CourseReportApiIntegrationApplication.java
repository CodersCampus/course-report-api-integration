package com.coderscampus.coursereportapiintegration;

import com.coderscampus.coursereportapiintegration.service.CourseReportService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CourseReportApiIntegrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseReportApiIntegrationApplication.class, args);


	}

}
