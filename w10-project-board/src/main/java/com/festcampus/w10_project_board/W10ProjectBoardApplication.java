package com.festcampus.w10_project_board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class W10ProjectBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(W10ProjectBoardApplication.class, args);
	}

}
