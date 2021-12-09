package mz.co.exchange.api.core.security.passwordReset.presentation;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import mz.co.exchange.api.core.security.passwordReset.model.PasswordResetCommand;
import mz.co.exchange.api.core.security.passwordReset.model.RequestPasswordResetCommand;
import mz.co.exchange.api.core.security.passwordReset.service.PasswordResetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/user/password", name = "password_reset")
@RequiredArgsConstructor
@Api(tags = "Password Reset")
public class PasswordResetController {
    private final PasswordResetService passwordResetService;

    @PostMapping("/email")
    public ResponseEntity<?> requestPasswordReset(@RequestBody RequestPasswordResetCommand command) {
        return ResponseEntity.ok(passwordResetService.requestPasswordResetLink(command));
    }

    @PutMapping("/reset")
    public ResponseEntity<?> resetPassword(@RequestBody PasswordResetCommand command) {
        return ResponseEntity.ok(passwordResetService.resetPassword(command));
    }
}
