package me.monotron.screwthisgame.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "me.monotron.screwthisgame.backend")
public class ScrewThisGameBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScrewThisGameBackendApplication.class, args);
    }
}
