package me.monotron.screwthisgame.backend.model;

import lombok.Builder;
import lombok.Data;
import me.monotron.screwthisgame.backend.enums.ClientType;

import java.time.LocalDateTime;

@Data
@Builder
public class Client {

    private ClientType type;
    private String clientId;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdatedDate;
}
