package mz.co.checkmob.api.user.notifications;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import mz.co.checkmob.api.notification.Notifiable;
import mz.co.checkmob.api.notification.Notification;
import mz.co.checkmob.api.notification.message.MailMessage;
import org.springframework.core.env.Environment;

@AllArgsConstructor
public class RequestPasswordResetNotification implements Notification {
    private final Environment env;
    private final String token;

    @SneakyThrows
    @Override
    public MailMessage toMailMessage(Notifiable notifiable) {
        return (MailMessage) new MailMessage()
                .setSubject("Instruções para recuperação de senha!")
                .setGreeting("Saudações!")
                .line("Caso tenha recebido este email por engano, nenhuma ação precisa ser feita")
                .action("Clique aqui para recuperar a senha", env.getProperty("application.baseUrl") +
                        "/auth/password/"+token)
                .setSalutation("Melhores cumprimentos");
    }
}

