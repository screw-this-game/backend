package me.monotron.screwthisgame.backend.model.response;

import lombok.Builder;
import lombok.Data;
import me.monotron.screwthisgame.backend.enums.RequestStatus;

@Data
@Builder
public class HealthCheckResponse {

    private RequestStatus status;
}
