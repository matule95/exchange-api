package mz.co.checkmob.api.user.events;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserCreatedEvent {
    private final String email;
    private final String username;
    private final String rawPassword;
}
