package com.disney.mundo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class MundoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MundoApplication.class, args);
	}

}
