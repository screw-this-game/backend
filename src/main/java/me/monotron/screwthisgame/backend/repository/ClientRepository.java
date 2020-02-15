package me.monotron.screwthisgame.backend.repository;

import me.monotron.screwthisgame.backend.model.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends MongoRepository<Client, String> {

    Client getFirstByClientId(String clientId);
}
