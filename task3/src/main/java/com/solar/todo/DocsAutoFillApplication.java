package com.solar.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DocsAutoFillApplication {
	public static void main(String[] args) {
		SpringApplication.run(DocsAutoFillApplication.class, args);
	}
}
