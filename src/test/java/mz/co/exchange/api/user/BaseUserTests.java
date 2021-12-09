package mz.co.exchange.api.user;

import mz.co.exchange.api.user.domain.CreateUserCommand;
import mz.co.exchange.api.user.domain.UpdateUserCommand;
import mz.co.exchange.api.user.domain.User;
import mz.co.exchange.api.user.domain.UserMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;

public class BaseUserTests {
    protected User getAnyUser() {
        return UserMapper.INSTANCE.mapToModel(getCreateUserCommand());
    }

    protected CreateUserCommand getCreateUserCommand() {
        CreateUserCommand command = new CreateUserCommand();
        command.setName("Test User");
        command.setUsername("testuser");
        command.setEmail("testuser@cowork.co.mz");
        command.setPassword("@admin123");
        return command;
    }

    protected Page<User> getAnyUserPage() {
        List<User> users = new ArrayList<>();
        users.add(getAnyUser());
        users.add(getAnyUser());
        users.add(getAnyUser());

        return new PageImpl<>(users);
    }

    protected UpdateUserCommand getAnyUpdateUserCommand() {
        UpdateUserCommand command = new UpdateUserCommand();
        command.setId(1L);
        command.setName("Admin");
        return command;
    }
}
