package me.monotron.screwthisgame.backend.model.response;

import lombok.Builder;
import lombok.Data;
import me.monotron.screwthisgame.backend.enums.RequestStatus;

import java.util.List;

@Data
@Builder
public class ClientRegistrationResponse {

    private RequestStatus status;
    private String clientId;
    private List<String> capabilities;

    public static ClientRegistrationResponse success(String clientId, List<String> capabilities) {
        return ClientRegistrationResponse.builder()
                .clientId(clientId)
                .status(RequestStatus.SUCCESS)
                .capabilities(capabilities)
                .build();
    }

    public static ClientRegistrationResponse fail(RequestStatus status) {
        return ClientRegistrationResponse.builder()
                .clientId("")
                .status(status)
                .build();
    }
}
