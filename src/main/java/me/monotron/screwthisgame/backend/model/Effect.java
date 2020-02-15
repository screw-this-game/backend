package me.monotron.screwthisgame.backend.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Effect {
    private String clientId;
    private String effectName;
    private String alias;
}
