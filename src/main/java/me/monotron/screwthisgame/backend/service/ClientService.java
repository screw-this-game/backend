package me.monotron.screwthisgame.backend.service;

import me.monotron.screwthisgame.backend.enums.ClientType;

public interface ClientService {
    String registerNewClient(ClientType type);
}
