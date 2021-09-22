package mz.co.checkmob.api.endpoint.presentation;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
public class EndpointJson {
    private Long id;
    private String url;
    private Map<String, Object> dataReader;
    private LocalDateTime createdAt;
}
