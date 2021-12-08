package mz.co.checkmob.api.company.presentation;

import lombok.Data;
import mz.co.checkmob.api.company.domain.CompanyStatus;
import mz.co.checkmob.api.endpoint.domain.Endpoint;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CompanyJson {
    private Long id;
    private String name;
    private String email;
    private CompanyStatus companyStatus;
    private String baseUrl;
    private String usernameCheckmob;
    private List<Endpoint> endpoints;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
