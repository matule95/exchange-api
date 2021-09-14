package mz.co.checkmob.api.core.security.passwordReset.model;

import lombok.Getter;
import lombok.Setter;
import mz.co.checkmob.api.core.security.passwordReset.events.PasswordResetCompleteEvent;
import mz.co.checkmob.api.core.security.passwordReset.events.PasswordResetRequestedEvent;
import mz.co.checkmob.api.user.domain.User;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "password_resets")
public class PasswordReset extends AbstractAggregateRoot<PasswordReset> {
    @Id
    private Long id;

    @Column(name = "user_email")
    private String userEmail;
    private String token;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "expires_at")
    private LocalDateTime expiresAt;

    public void requestedResetLink(String email, String token) {
        registerEvent(new PasswordResetRequestedEvent(email, token));
    }

    public void passwordResetComplete(User u) {
        registerEvent(new PasswordResetCompleteEvent(u));
    }
}
