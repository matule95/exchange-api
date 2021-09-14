package mz.co.checkmob.api.user.service;

import lombok.RequiredArgsConstructor;
import mz.co.checkmob.api.user.domain.*;
import mz.co.checkmob.api.user.domain.query.UserQuery;
import mz.co.checkmob.api.user.persistence.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserQueryGateway userQueryGateway;

    @Override
    @Transactional
    public User create(CreateUserCommand command) {
        if (!validateFields(command)) return null;

        User user = UserMapper.INSTANCE.mapToModel(command);
        user.setPassword(new BCryptPasswordEncoder().encode(command.getPassword()));
        user.created(user.getUsername(), command.getPassword());
        return userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("O usuário pretendido não foi encontrado."));
    }

    @Override
    public Page<User> fetchPaged(Pageable pageable, UserQuery userQuery) {
        return userQueryGateway.execute(userQuery, pageable);
    }

    @Override
    public void deleteById(Long id) {
        User user = findById(id);
        userRepository.deleteById(user.getId());
    }

    @Override
    public User update(UpdateUserCommand command) {
        User user = findById(command.getId());
        UserMapper.INSTANCE.updateModel(user, command);

        return userRepository.save(user);
    }

    private boolean validateFields(CreateUserCommand command) {
        if (command.getUsername() == null || command.getUsername().isEmpty())
            throw new RuntimeException("O campo Username é obrigatório.");

        return true;
    }
}
