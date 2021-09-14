package mz.co.checkmob.api.user.notifications;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import mz.co.checkmob.api.notification.Notifiable;
import mz.co.checkmob.api.notification.Notification;
import mz.co.checkmob.api.notification.message.MailMessage;
import org.springframework.core.env.Environment;

@RequiredArgsConstructor
public class UserCreatedNotification implements Notification {
    private final Environment env;
    private final String username;
    private final String rawPassword;

    @SneakyThrows
    @Override
    public MailMessage toMailMessage(Notifiable notifiable) {
        return (MailMessage) new MailMessage()
                .setSubject("Credenciais de acesso a sua conta do INAE")
                .setGreeting("Saudações!")
                .line("Username: "+ username)
                .line("Senha: "+ rawPassword)
                .action("Clique aqui para aceder a tela de login", env.getProperty("application.baseUrl") + "/auth/login/")
                .setSalutation("Melhores cumprimentos");
    }
}

