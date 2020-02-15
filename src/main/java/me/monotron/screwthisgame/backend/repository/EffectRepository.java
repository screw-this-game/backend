package me.monotron.screwthisgame.backend.repository;

import me.monotron.screwthisgame.backend.model.Effect;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EffectRepository extends MongoRepository<Effect, String> {

    List<Effect> getByClientId(String clientId);
}
