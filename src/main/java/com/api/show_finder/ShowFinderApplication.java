package com.api.show_finder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ShowFinderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShowFinderApplication.class, args);
	}

}
