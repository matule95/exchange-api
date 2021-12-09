package mz.co.exchange.api.core.security.passwordReset.service;

import lombok.RequiredArgsConstructor;
import mz.co.exchange.api.core.security.exceptions.InvalidTokenException;
import mz.co.exchange.api.core.security.exceptions.PasswordValidationException;
import mz.co.exchange.api.core.security.passwordReset.model.PasswordReset;
import mz.co.exchange.api.core.security.passwordReset.model.PasswordResetCommand;
import mz.co.exchange.api.core.security.passwordReset.model.RequestPasswordResetCommand;
import mz.co.exchange.api.core.security.passwordReset.persistence.PasswordResetRepository;
import mz.co.exchange.api.user.domain.User;
import mz.co.exchange.api.user.persistence.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PasswordResetServiceImpl implements PasswordResetService {
    private final PasswordResetRepository passwordResetRepository;
    private final UserRepository userRepository;

    @Override
    public User resetPassword(PasswordResetCommand command) {
        validatePasswords(command.getPassword(), command.getConfirmPassword());

        PasswordReset passwordReset = passwordResetRepository.findByToken(command.getToken());
        validateToken(passwordReset);

        Optional<User> user = userRepository.findByEmail(passwordReset.getUserEmail());

        if (user.isPresent()) {
            User u = user.get();
            u.setPassword(new BCryptPasswordEncoder().encode(command.getPassword()));
            passwordReset.passwordResetComplete(u);
            return userRepository.save(u);
        }
        return null;
    }

    private void validateToken(PasswordReset passwordReset) {
        if (Objects.isNull(passwordReset)) throw new InvalidTokenException("Token inválido.");
        // TODO check if token has expired
    }

    private void validatePasswords(String password, String confirmPassword) throws PasswordValidationException {
        if (password.length() < 6) throw new PasswordValidationException("O Campo requer o minimo de 6 caracteres.");
        if (!password.equals(confirmPassword)) throw new PasswordValidationException("As senhas fornecidas não conscidem.");
    }

    @Override
    public PasswordReset requestPasswordResetLink(RequestPasswordResetCommand command) {
        User user = userRepository.findByEmail(command.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("O E-mail fornecido não está associado a nenhuma conta."));

        PasswordReset passwordReset = new PasswordReset();
        String token = UUID.randomUUID().toString();
        passwordReset.setUserEmail(user.getEmail());
        passwordReset.setToken(token);
        passwordReset.setExpiresAt(LocalDateTime.now().plusHours(1));
        passwordReset.requestedResetLink(user.getEmail(), token);

        return passwordResetRepository.save(passwordReset);
    }
}
