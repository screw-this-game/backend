package me.monotron.screwthisgame.backend.model;

import lombok.Builder;
import lombok.Data;
import me.monotron.screwthisgame.backend.enums.RequestStatus;

import java.util.List;

@Data
@Builder
public class ClientEffectsResponse {

    private RequestStatus status;
    private List<String> effects;
}
