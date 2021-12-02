package mz.co.checkmob.api.connections.presentation;

import lombok.AllArgsConstructor;
import lombok.Data;
import mz.co.checkmob.api.connections.domain.Param;
import mz.co.checkmob.api.connections.domain.RequestType;
import mz.co.checkmob.api.endpoint.domain.Endpoint;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class ConnectionJson {
    private Long id;
    private Endpoint fromThirdParty;
    private String fromUrl;
    private RequestType fromRequestType;

    private Endpoint toThirdParty;
    private String name;

    private String toUrl;
    private RequestType toRequestType;

    private LocalDateTime createdAt;

    private List<Param> params;
}
