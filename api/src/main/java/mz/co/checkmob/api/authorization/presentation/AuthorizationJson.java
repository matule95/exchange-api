package mz.co.checkmob.api.authorization.presentation;

import lombok.Data;
import mz.co.checkmob.api.authorization.domain.AuthorizationType;
import java.time.LocalDateTime;
import java.util.Map;

@Data
public class AuthorizationJson {
    private Long id;
    private AuthorizationType type;
    private Map<String, Object> authJson;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}