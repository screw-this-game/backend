package me.monotron.screwthisgame.backend.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.monotron.screwthisgame.backend.model.Client;
import me.monotron.screwthisgame.backend.model.Effect;
import me.monotron.screwthisgame.backend.repository.ClientRepository;
import me.monotron.screwthisgame.backend.repository.FrontendRepository;
import me.monotron.screwthisgame.backend.service.FrontendService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.validation.ValidationException;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.Objects.nonNull;

@Service
@Slf4j
@AllArgsConstructor
public class FrontendServiceImpl implements FrontendService {

    FrontendRepository frontendRepository;
    ClientRepository clientRepository;

    @Override
    public void addEffect(String effectName, String clientId) {

        if(!clientExists(clientId)) {
            throw new ValidationException("The specified client ID does not exist.");
        }

        updateLastCheckedTimestamp(clientId);

        Effect effect = Effect.builder()
                .effectName(effectName)
                .clientId(clientId)
                .build();

        frontendRepository.save(effect);
    }

    @Override
    public List<Client> getClients() {
        return clientRepository.findAll();
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
