package mz.co.checkmob.api;

import com.github.javafaker.Faker;
import mz.co.checkmob.api.authorization.domain.Authorization;
import mz.co.checkmob.api.authorization.domain.AuthorizationCommand;
import mz.co.checkmob.api.authorization.domain.AuthorizationType;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@ExtendWith(MockitoExtension.class)
public abstract class AbstractTest {
    private final Faker faker = new Faker();

    protected final Faker faker() {
        return faker;
    }
    protected Authorization anyAuthorization(){
        Authorization authorization = new Authorization();
        authorization.setId(ThreadLocalRandom.current().nextLong(10,100));
        authorization.setType(faker.options().option(AuthorizationType.values()));
        authorization.setAuthJson(Map.of(faker.internet().slug(), faker.internet().url()));
        authorization.setCreatedAt(LocalDateTime.now());
        authorization.setUpdatedAt(LocalDateTime.now());
        return authorization;
    }
    protected AuthorizationCommand anyAuthorizationCommand(){
        AuthorizationCommand command = new AuthorizationCommand();
        command.setType(faker.options().option(AuthorizationType.values()));
        command.setAuthJson(Map.of(faker.internet().slug(), faker.internet().url()));
        return command;
    }

}