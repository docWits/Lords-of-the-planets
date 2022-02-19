package com.romanyuta.lords_of_the_planets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.sql.SQLException;

@SpringBootApplication
@EnableScheduling
public class LordsOfThePlanetsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LordsOfThePlanetsApplication.class, args);
	}

}
