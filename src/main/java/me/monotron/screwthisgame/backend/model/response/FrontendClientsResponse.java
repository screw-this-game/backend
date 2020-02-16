package me.monotron.screwthisgame.backend.model.response;

import lombok.Builder;
import lombok.Data;
import me.monotron.screwthisgame.backend.model.Client;

import java.util.List;

@Data
@Builder
public class FrontendClientsResponse {

    private List<Client> clients;
}
