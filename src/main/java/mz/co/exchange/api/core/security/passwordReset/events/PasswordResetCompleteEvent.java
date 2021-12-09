package mz.co.exchange.api.core.security.passwordReset.events;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import mz.co.exchange.api.user.domain.User;

@Getter
@RequiredArgsConstructor
public class PasswordResetCompleteEvent {
    private final User user;
}
