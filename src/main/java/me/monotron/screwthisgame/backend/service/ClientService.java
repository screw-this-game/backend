package me.monotron.screwthisgame.backend.service;

import me.monotron.screwthisgame.backend.enums.ClientType;

import java.util.List;

public interface ClientService {
    String registerNewClient(ClientType type);

    List<String> getEffects(String clientId);
}
