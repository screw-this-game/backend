package me.monotron.screwthisgame.backend.controller;

import com.twilio.twiml.MessagingResponse;
import com.twilio.twiml.messaging.Body;
import com.twilio.twiml.messaging.Message;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.monotron.screwthisgame.backend.service.TwilioService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@AllArgsConstructor
public class TwilioController {

    TwilioService twilioService;

    @GetMapping(value = "/twilio/receive", produces = "text/xml")
    public ResponseEntity<String> receiveMessage(
        @RequestParam(name = "Body") String message
    ) {

        String[] args = message.split(" ");
        if(args.length < 2) {
            String response = new MessagingResponse.Builder()
                    .message(new Message.Builder()
                            .body(new Body.Builder("Please send a game ID and the effect you wish to apply. For example, \"abcdef fire\".")
                                    .build())
                            .build())
                    .build().toXml();
            return ResponseEntity.ok(response);
        }

        String twilioId = args[0].toLowerCase();
        String effectName = args[1];

        String response = new MessagingResponse.Builder()
            .message(new Message.Builder()
                        .body(new Body.Builder(twilioService.queueEffect(twilioId, effectName))
                        .build())
                .build())
            .build().toXml();

        return ResponseEntity.ok(response);
    }
}
