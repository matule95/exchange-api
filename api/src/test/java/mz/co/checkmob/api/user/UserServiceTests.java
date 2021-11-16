package mz.co.checkmob.api.user;

import mz.co.checkmob.api.user.domain.*;
import mz.co.checkmob.api.user.domain.query.UserQuery;
import mz.co.checkmob.api.user.events.UserCreatedEvent;
import mz.co.checkmob.api.user.persistence.UserRepository;
import mz.co.checkmob.api.user.service.UserService;
import mz.co.checkmob.api.user.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests extends BaseUserTests {
    @Autowired
    private UserService underTest;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserQueryGateway userQueryGateway;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @Captor
    private ArgumentCaptor<UserCreatedEvent> userCreatedEventArgumentCaptor;

    private CreateUserCommand command;

    @BeforeEach
    void setUp() {
        command = getCreateUserCommand();
        underTest = new UserServiceImpl(userRepository, userQueryGateway);
    }

    @Test
    void itShouldCreateUser() {
        // when
        when(userRepository.save(any())).thenReturn(getAnyUser());

        // then
        underTest.create(command);
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(captor.capture());
//        verify(eventPublisher).publishEvent(userCreatedEventArgumentCaptor.capture());

        assertThat(captor.getValue().getPassword()).isNotEqualTo(command.getPassword());
    }

    @Test
    void itShouldThrowExceptionIfUsernameIsNotPresentOrNull() {
        // given
        command.setUsername("");

        // when
        // then
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> underTest.create(command))
                .withMessage("O campo Username é obrigatório.");
    }

    @Test
    void itShouldFetchUserById() {
        // given
        Long userId = 1L;
        User mockUser = getAnyUser().setId(userId);

        // when
        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));
        User expected = underTest.findById(userId);

        // then
        verify(userRepository).findById(userId);
        assertThat(expected).isEqualTo(mockUser);
    }

    @Test
    void itShouldThrowExceptionIfUserDoesNotExist() {
        // when
        // then
        assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(() -> underTest.findById(any()))
                .withMessage("O usuário pretendido não foi encontrado.");
        verify(userRepository, never()).deleteById(any());
    }

    @Test
    void itShouldFetchAPageOfUsers() {
        // given
        UserQuery userQuery = new UserQuery();
        Pageable pageable = Pageable.unpaged();

        // when
        when(userQueryGateway.execute(userQuery, pageable)).thenReturn(getAnyUserPage());
        Page<User> users = underTest.fetchPaged(pageable, userQuery);

        // then
        verify(userQueryGateway).execute(userQuery, pageable);
        assertThat(users.getTotalElements()).isEqualTo(getAnyUserPage().getTotalElements());
    }

    @Test
    void itShouldDeleteAUserByGivenId() {
        // given
        // when
        when(userRepository.findById(1L)).thenReturn(Optional.of(getAnyUser().setId(1L)));
        underTest.deleteById(1L);

        // then
        verify(userRepository).findById(1L);
        verify(userRepository).deleteById(1L);
    }

    @Test
    void itShouldUpdateUserDetails() {
        // given
        UpdateUserCommand command = getAnyUpdateUserCommand();
        User toUpdate = getAnyUser().setId(command.getId());

        User userUpdateMock = new User();
        UserMapper.INSTANCE.cloneModel(userUpdateMock, toUpdate);

        User updatedMock = new User();
        UserMapper.INSTANCE.cloneModel(updatedMock, toUpdate);
        updatedMock.setName(command.getName());

        // when
        when(userRepository.findById(command.getId())).thenReturn(Optional.of(userUpdateMock));
        when(userRepository.save(any())).thenReturn(updatedMock);

        User expected = underTest.update(command,command.getId());
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);

        // then
        verify(userRepository).save(captor.capture());
        assertThat(expected).isNotNull();
        assertThat(expected.getId()).isEqualTo(toUpdate.getId());
        assertThat(expected.getName()).isNotEqualTo(toUpdate.getName());
    }
}
