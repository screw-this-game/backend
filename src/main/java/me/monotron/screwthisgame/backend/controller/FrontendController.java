package me.monotron.screwthisgame.backend.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.monotron.screwthisgame.backend.enums.RequestStatus;
import me.monotron.screwthisgame.backend.model.response.FrontendClientsResponse;
import me.monotron.screwthisgame.backend.model.response.GenericResponse;
import me.monotron.screwthisgame.backend.service.FrontendService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;

@RestController
@Slf4j
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "https://screw-this-game.netlify.com"})
public class FrontendController {

    FrontendService frontendService;

    @PutMapping(value = "/frontend/effects/{clientId}")
    public ResponseEntity<GenericResponse> addEffect(
        @PathVariable("clientId") String clientId,
        @RequestParam("effectName") String effectName
    ) {

        try {
            frontendService.addEffect(effectName, clientId);
        } catch (ValidationException e) {
            return ResponseEntity.badRequest()
                .body(GenericResponse.builder()
                    .status(RequestStatus.INVALID_CLIENT_ID)
                    .build());
        }

        log.info("Added effect {} for client ID {}", effectName, clientId);

        return ResponseEntity.ok(GenericResponse.builder()
                .status(RequestStatus.SUCCESS)
                .build());
    }

    @GetMapping(value = "/frontend/clients")
    public ResponseEntity<FrontendClientsResponse> getClients() {

        return ResponseEntity.ok(FrontendClientsResponse.builder()
                .clients(frontendService.getClients())
                .build());
    }
}
