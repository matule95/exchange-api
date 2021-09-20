package mz.co.checkmob.api.company.presentation;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CompanyJson {
    private Long id;
    private String name;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}