package mz.co.exchange.api.company.domain;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class UpdateCompanyCommand {
    private Long id;
    @NotNull
    @NotEmpty
    private String name;
    private String path;
    @NotNull
    @NotEmpty
    private String email;
}
