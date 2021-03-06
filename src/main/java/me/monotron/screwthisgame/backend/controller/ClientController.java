package me.monotron.screwthisgame.backend.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.monotron.screwthisgame.backend.enums.ClientType;
import me.monotron.screwthisgame.backend.model.request.ClientRegistrationRequest;
import me.monotron.screwthisgame.backend.model.response.ClientEffectsResponse;
import me.monotron.screwthisgame.backend.model.response.ClientRegistrationResponse;
import me.monotron.screwthisgame.backend.model.response.GenericResponse;
import me.monotron.screwthisgame.backend.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;

import java.util.List;

import static me.monotron.screwthisgame.backend.enums.RequestStatus.*;

@RestController
@Slf4j
@AllArgsConstructor
public class ClientController {

    ClientService clientService;

    @GetMapping(value = "/client/health")
    public ResponseEntity<GenericResponse> healthCheck() {

        return ResponseEntity.ok(GenericResponse.builder().status(SUCCESS).build());
    }

    @PostMapping(value = "/client/register")
    public ResponseEntity<ClientRegistrationResponse> registerClient(
        @RequestHeader("X-Client-Type") String clientType,
        @RequestBody ClientRegistrationRequest requestBody
    ) {

        ClientType type;

        try {
            type = getClientType(clientType);
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(ClientRegistrationResponse.fail(INVALID_CLIENT_TYPE));
        }

        log.info("Creating a new client, game = {}", type);

        List<String> capabilities = requestBody.getCapabilities();
        String clientId = clientService.registerNewClient(type, capabilities);

        return ResponseEntity.ok().body(ClientRegistrationResponse.success(clientId, capabilities));
    }

    @GetMapping(value = "/client/{clientId}/effects")
    public ResponseEntity<ClientEffectsResponse> getEffects(
        @PathVariable("clientId") String clientId
    ) {

        log.info("Getting effects for client with ID: {}", clientId);

        List<String> effects;

        try {
            effects = clientService.getEffects(clientId);
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(ClientEffectsResponse.builder().status(INVALID_CLIENT_ID).build());
        }

        ClientEffectsResponse response = ClientEffectsResponse.builder()
                .effects(effects)
                .status(SUCCESS)
                .build();

        return ResponseEntity.ok(response);
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
