package mz.co.exchange.api.core.security.passwordReset.events;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class PasswordResetEventHandler {
    @Async
    @EventListener
    public void handlePasswordResetRequestedEvent(PasswordResetRequestedEvent event) {
        System.out.println(event.getEmail());
    }

    @Async
    @EventListener
    public void handlePasswordResetCompleteEvent(PasswordResetCompleteEvent event) {
        System.out.println(event.getUser().getEmail());
    }
}
