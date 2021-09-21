package mz.co.checkmob.api.authorization.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorizationCommand {
    private AuthorizationType type;
    private Map<String, Object> authJson;
}