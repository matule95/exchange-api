package mz.co.checkmob.api.core.security.passwordReset.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordResetCommand {
    private String password;
    private String confirmPassword;
    private String token;
}
