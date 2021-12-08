package mz.co.checkmob.api.endpoint.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mz.co.checkmob.api.authorization.domain.AuthorizationType;
import mz.co.checkmob.api.company.domain.Company;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEndpointCommand {
    private String name;
    private String url;
    private Long companyId;
    private AuthorizationType authenticationType;
    private Map<String, Object> dataReader;
}
