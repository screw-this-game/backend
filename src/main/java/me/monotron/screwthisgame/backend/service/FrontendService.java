package me.monotron.screwthisgame.backend.service;

import me.monotron.screwthisgame.backend.model.Client;

import java.util.List;

public interface FrontendService {

    void addEffect(String effectName, String clientId);

    List<Client> getClients();
}
