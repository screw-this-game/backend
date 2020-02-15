package me.monotron.screwthisgame.backend.repository;

import me.monotron.screwthisgame.backend.model.Effect;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FrontendRepository extends MongoRepository<Effect, String> {

}
