package mz.co.checkmob.api.core.security.passwordReset.events;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PasswordResetRequestedEvent {
    private final String email;
    private final String token;
}
