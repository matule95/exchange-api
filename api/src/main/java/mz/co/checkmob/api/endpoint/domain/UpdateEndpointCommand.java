package mz.co.checkmob.api.endpoint.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEndpointCommand {
    private Long id;
    private String url;
    private String path;
    private Map<String, Object> dataReader;
}
