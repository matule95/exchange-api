package mz.co.checkmob.api.user.events;

import lombok.RequiredArgsConstructor;
import mz.co.checkmob.api.notification.Notifiable;
import mz.co.checkmob.api.user.notifications.UserCreatedNotification;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserCreatedEventHandler {
    private final Environment env;

    @Async
    @EventListener
    public void handleSendEmailNotification(UserCreatedEvent event) {
        Notifiable.of(event.getEmail()).sendNotification(new UserCreatedNotification(env, event.getUsername(), event.getRawPassword()));
    }
}
