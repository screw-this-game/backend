package me.monotron.screwthisgame.backend.repository;

import me.monotron.screwthisgame.backend.model.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

public abstract class ClientRepository implements MongoRepository<Client, String> {
}
