package me.monotron.screwthisgame.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import me.monotron.screwthisgame.backend.enums.ClientType;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class Client {

    @Id
    @JsonIgnore
    private String id;

    private ClientType type;
    private String clientId;
    private String twilioId;
    private List<String> capabilities;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdatedDate;
}
