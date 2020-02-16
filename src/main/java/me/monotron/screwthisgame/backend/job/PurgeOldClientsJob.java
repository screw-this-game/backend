package me.monotron.screwthisgame.backend.job;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.monotron.screwthisgame.backend.repository.ClientRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
@AllArgsConstructor
public class PurgeOldClientsJob {

    ClientRepository clientRepository;

    @Scheduled(cron = "* * * * */10 *")
    private void removeOldClients() {

        log.info("Purging old clients");

        clientRepository.findAll().stream()
                .filter(client -> client.getLastUpdatedDate().isBefore(LocalDateTime.now().minusMinutes(10)))
                .forEach(clientRepository::delete);
    }
}
