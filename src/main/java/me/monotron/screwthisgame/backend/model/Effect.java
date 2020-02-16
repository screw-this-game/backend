package me.monotron.screwthisgame.backend.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class Effect {

    @Id private String id;

    private String clientId;
    private String effectName;
    private String alias;
}
