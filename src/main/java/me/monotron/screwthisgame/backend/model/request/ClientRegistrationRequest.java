package me.monotron.screwthisgame.backend.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@Value
public class ClientRegistrationRequest {

    @JsonProperty("capabilities")
    private List<String> capabilities;
}
