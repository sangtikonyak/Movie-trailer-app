package com.capstone.WatchLater;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
@SpringBootApplication
@EnableEurekaClient
public class WatchLaterApplication {

	public static void main(String[] args) {
		SpringApplication.run(WatchLaterApplication.class, args);
	}

}
