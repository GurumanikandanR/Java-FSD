package com.springboot.crime_management_system_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class CrimeManagementSystemBackendApplication {

	public static void main(String[] args) {

		SpringApplication.run(CrimeManagementSystemBackendApplication.class, args);
	}

}
