package me.monotron.screwthisgame.backend.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.monotron.screwthisgame.backend.model.Client;
import me.monotron.screwthisgame.backend.model.Effect;
import me.monotron.screwthisgame.backend.repository.ClientRepository;
import me.monotron.screwthisgame.backend.repository.EffectRepository;
import me.monotron.screwthisgame.backend.service.TwilioService;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;

@Service
@Slf4j
@AllArgsConstructor
public class TwilioServiceImpl implements TwilioService {

    ClientRepository clientRepository;
    EffectRepository effectRepository;

    @Override
    public String queueEffect(String twilioId, String effectName) {
        Client client = clientRepository.getFirstByTwilioId(twilioId);

        if(client == null) {
            return "Sorry, that game doesn't seem to exist. Please try again.";
        }

        Effect effect = Effect.builder()
                .clientId(client.getClientId())
                .effectName(effectName)
                .build();

        effectRepository.save(effect);

        return "Done! That effect has been registered.";
    }
}
