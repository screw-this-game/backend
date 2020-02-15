package me.monotron.screwthisgame.backend.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.monotron.screwthisgame.backend.enums.ClientType;
import me.monotron.screwthisgame.backend.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    // TODO add repository

    @Override
    public String registerNewClient(ClientType type) {
        String clientId = generateClientId();

        // TODO save to db
        return clientId;
    }

    private String generateClientId() {
        return UUID.randomUUID().toString();
    }
}
