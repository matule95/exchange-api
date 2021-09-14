package mz.co.checkmob.api.core.security.passwordReset.persistence;


import mz.co.checkmob.api.core.security.passwordReset.model.PasswordReset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetRepository extends JpaRepository<PasswordReset, Long> {
    PasswordReset findByToken(String token);
}
