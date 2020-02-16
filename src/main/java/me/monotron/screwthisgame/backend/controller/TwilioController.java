package me.monotron.screwthisgame.backend.controller;

import com.twilio.Twilio;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Slf4j
@AllArgsConstructor
public class TwilioController {

    @PostMapping(value = "/twilio/receive")
    public ResponseEntity<String> receiveMessage(@RequestBody String body) {
        log.info(body);
        return ResponseEntity.ok("");
    }
}
