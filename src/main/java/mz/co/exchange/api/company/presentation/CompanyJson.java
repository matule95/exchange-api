package mz.co.exchange.api.company.presentation;

import lombok.Data;
import mz.co.exchange.api.company.domain.CompanyStatus;

import java.time.LocalDateTime;

@Data
public class CompanyJson {
    private Long id;
    private String name;
    private String email;
    private CompanyStatus companyStatus;
    private String baseUrl;
    private String usernameexchange;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
