package me.monotron.screwthisgame.backend.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.monotron.screwthisgame.backend.enums.ClientType;
import me.monotron.screwthisgame.backend.model.Client;
import me.monotron.screwthisgame.backend.model.Effect;
import me.monotron.screwthisgame.backend.repository.ClientRepository;
import me.monotron.screwthisgame.backend.repository.EffectRepository;
import me.monotron.screwthisgame.backend.service.ClientService;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Service
@Slf4j
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    ClientRepository clientRepository;
    EffectRepository effectRepository;

    @Override
    public String registerNewClient(ClientType type) {
        String clientId = generateClientId();
        Client client = Client.builder()
                .clientId(clientId)
                .type(type)
                .creationDate(LocalDateTime.now())
                .lastUpdatedDate(LocalDateTime.now())
                .build();

        clientRepository.save(client);

        return clientId;
    }

    @Override
    public List<String> getEffects(String clientId) {

        if(!clientExists(clientId)) {
            throw new ValidationException("The specified client does not exist.");
        }

        updateLastCheckedTimestamp(clientId);

        return effectRepository.getAllByClientId(clientId).stream()
                .map(Effect::getEffectName).collect(Collectors.toList());
    }

    private String generateClientId() {
        return UUID.randomUUID().toString();
    }

    private boolean clientExists(String clientId) {
        return nonNull(clientRepository.getFirstByClientId(clientId));
    }

    private void updateLastCheckedTimestamp(String clientId) {
        Client client = clientRepository.getFirstByClientId(clientId);
        client.setLastUpdatedDate(LocalDateTime.now());
        clientRepository.save(client);
    }
}
