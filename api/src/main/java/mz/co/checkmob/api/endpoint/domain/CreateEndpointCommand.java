package mz.co.checkmob.api.endpoint.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEndpointCommand {
    private String url;
    private Map<String, Object> dataReader;
}
