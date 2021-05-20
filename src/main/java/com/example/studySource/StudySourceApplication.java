package com.example.studySource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class StudySourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudySourceApplication.class, args);
	}

}
