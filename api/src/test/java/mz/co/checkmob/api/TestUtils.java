package mz.co.checkmob.api;

import mz.co.checkmob.api.user.domain.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

public class TestUtils {
    public static User getAnyUser() {
        User user = new User();
        user.setId(1L);
        user.setName("Administrator");
        user.setEmail("admin@technoplus.co.mz");
        user.setPassword(new BCryptPasswordEncoder().encode("@Admin123"));
        user.setUsername("admin");
        user.setCreatedAt(LocalDateTime.now());

        return user;
    }
}
