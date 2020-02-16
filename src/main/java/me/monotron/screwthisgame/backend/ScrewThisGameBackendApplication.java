package me.monotron.screwthisgame.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "me.monotron.screwthisgame.backend")
@EnableMongoRepositories(basePackages = "me.monotron.screwthisgame.backend")
@EnableScheduling
public class ScrewThisGameBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScrewThisGameBackendApplication.class, args);
    }
}
