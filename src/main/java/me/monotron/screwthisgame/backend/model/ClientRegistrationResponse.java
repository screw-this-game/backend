package me.monotron.screwthisgame.backend.model;

import lombok.Builder;
import lombok.Data;
import me.monotron.screwthisgame.backend.enums.RequestStatus;

@Data
@Builder
public class ClientRegistrationResponse {

    private RequestStatus status;
    private String clientId;

    public static ClientRegistrationResponse success(String clientId) {
        return ClientRegistrationResponse.builder()
                .clientId(clientId)
                .status(RequestStatus.SUCCESS)
                .build();
    }

    public static ClientRegistrationResponse fail(RequestStatus status) {
        return ClientRegistrationResponse.builder()
                .clientId("")
                .status(status)
                .build();
    }
}
