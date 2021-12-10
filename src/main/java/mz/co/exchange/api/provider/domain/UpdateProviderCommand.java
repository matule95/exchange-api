package mz.co.exchange.api.provider.domain;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UpdateProviderCommand {
    private Long id;
    private String name;
    private String email;
}
