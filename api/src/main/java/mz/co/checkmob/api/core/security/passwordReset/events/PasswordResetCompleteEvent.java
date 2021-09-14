package mz.co.checkmob.api.core.security.passwordReset.events;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import mz.co.checkmob.api.user.domain.User;

@Getter
@RequiredArgsConstructor
public class PasswordResetCompleteEvent {
    private final User user;
}
