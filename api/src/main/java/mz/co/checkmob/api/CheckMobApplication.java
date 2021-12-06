package mz.co.checkmob.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CheckMobApplication {

	public static void main(String[] args) {
		SpringApplication.run(CheckMobApplication.class, args);
	}

}
