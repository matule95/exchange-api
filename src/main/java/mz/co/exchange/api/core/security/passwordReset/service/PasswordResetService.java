package mz.co.exchange.api.core.security.passwordReset.service;

import mz.co.exchange.api.core.security.passwordReset.model.PasswordReset;
import mz.co.exchange.api.core.security.passwordReset.model.PasswordResetCommand;
import mz.co.exchange.api.core.security.passwordReset.model.RequestPasswordResetCommand;
import mz.co.exchange.api.user.domain.User;

public interface PasswordResetService {
    User resetPassword(PasswordResetCommand command);
    PasswordReset requestPasswordResetLink(RequestPasswordResetCommand command);
}
