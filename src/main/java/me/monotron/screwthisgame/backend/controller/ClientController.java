package me.monotron.screwthisgame.backend.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.monotron.screwthisgame.backend.enums.ClientType;
import me.monotron.screwthisgame.backend.model.ClientRegistrationResponse;
import me.monotron.screwthisgame.backend.model.HealthCheckResponse;
import me.monotron.screwthisgame.backend.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.validation.ValidationException;

import static me.monotron.screwthisgame.backend.enums.RequestStatus.INVALID_CLIENT_TYPE;
import static me.monotron.screwthisgame.backend.enums.RequestStatus.SUCCESS;

@Controller
@Slf4j
@AllArgsConstructor
public class ClientController {

    ClientService clientService;

    @GetMapping(value = "/client/health")
    public ResponseEntity<HealthCheckResponse> healthCheck() {

        return ResponseEntity.ok(HealthCheckResponse.builder().status(SUCCESS).build());
    }

    @PostMapping(value = "/client/register")
    public ResponseEntity<ClientRegistrationResponse> registerClient(
        @RequestHeader("X-Client-Type") String clientType
    ) {

        ClientType type;

        try {
            type = getClientType(clientType);
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(ClientRegistrationResponse.fail(INVALID_CLIENT_TYPE));
        }

        log.info("Creating a new client, game = {}", type);

        String clientId = clientService.registerNewClient(type);

        return ResponseEntity.ok().body(ClientRegistrationResponse.success(clientId));
    }

    private ClientType getClientType(String clientType) {
        ClientType type;

        try {
            type = ClientType.valueOf(clientType.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new ValidationException();
        }

        return type;
    }
}
