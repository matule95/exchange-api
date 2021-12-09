package mz.co.exchange.api.company.domain;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CreateCompanyCommand {
    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String email;

    @NotNull
    @NotEmpty
    private String baseUrl;

    private String path;
    @NotNull
    @NotEmpty
    private String usernameexchange;

    @NotNull
    @NotEmpty
    private String passwordexchange;

    @NotNull
    @NotEmpty
    private CompanyStatus companyStatus;
}