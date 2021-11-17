package mz.co.checkmob.api.endpoint.presentation;

import lombok.AllArgsConstructor;
import lombok.Data;
import mz.co.checkmob.api.authorization.domain.AuthorizationType;
import mz.co.checkmob.api.company.domain.Company;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
public class EndpointJson {
    private Long id;
    private String name;
    private String url;
    private Company company;
    private AuthorizationType authenticationType;
    private Map<String, Object> dataReader;
    private LocalDateTime createdAt;
}
