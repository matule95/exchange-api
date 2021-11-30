package mz.co.checkmob.api.connections.presentation;

import lombok.AllArgsConstructor;
import lombok.Data;
import mz.co.checkmob.api.authorization.domain.AuthorizationType;
import mz.co.checkmob.api.company.domain.Company;
import mz.co.checkmob.api.connections.domain.RequestType;
import mz.co.checkmob.api.endpoint.domain.Endpoint;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
public class ConnectionJson {
    private Long id;
    private Long fromThirdParty;
    private String fromUrl;
    private RequestType fromRequestType;

    private String name;

    private Long toThirdParty;
    private String toUrl;
    private RequestType toRequestType;

    private LocalDateTime createdAt;
}
