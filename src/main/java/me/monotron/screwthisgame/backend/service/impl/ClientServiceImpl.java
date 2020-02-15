package me.monotron.screwthisgame.backend.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.monotron.screwthisgame.backend.enums.ClientType;
import me.monotron.screwthisgame.backend.model.Client;
import me.monotron.screwthisgame.backend.repository.ClientRepository;
import me.monotron.screwthisgame.backend.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    ClientRepository clientRepository;

    @Override
    public String registerNewClient(ClientType type) {
        String clientId = generateClientId();
        Client client = Client.builder()
                .clientId(clientId)
                .type(type)
                .build();

        clientRepository.save(client);

        return clientId;
    }

    private String generateClientId() {
        return UUID.randomUUID().toString();
    }
}
