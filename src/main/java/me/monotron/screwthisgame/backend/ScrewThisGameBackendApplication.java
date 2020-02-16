package me.monotron.screwthisgame.backend;

import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;

@SpringBootApplication(scanBasePackages = "me.monotron.screwthisgame.backend")
@EnableMongoRepositories(basePackages = "me.monotron.screwthisgame.backend")
@EnableScheduling
public class ScrewThisGameBackendApplication {

    @Value("${twilio.account-sid}")
    String twilioSid;

    @Value("${twilio.auth-token}")
    String authToken;

    public static void main(String[] args) {
        SpringApplication.run(ScrewThisGameBackendApplication.class, args);
    }

    @PostConstruct
    public void initTwilio() {
        Twilio.init(twilioSid, authToken);
    }
}
