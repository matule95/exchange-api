package mz.co.checkmob.api.user;

import mz.co.checkmob.api.user.domain.CreateUserCommand;
import mz.co.checkmob.api.user.domain.query.UserQuery;
import mz.co.checkmob.api.user.presentation.UserController;
import mz.co.checkmob.api.user.presentation.UserJson;
import mz.co.checkmob.api.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTests extends BaseUserTests {
    private UserController underTest;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        underTest = new UserController(userService);
    }

    @Test
    void itShouldCreateUser() {
        // given
        CreateUserCommand command = getCreateUserCommand();

        // when
        when(userService.create(command)).thenReturn(getAnyUser().setId(1L));
        ResponseEntity<UserJson> response = underTest.createUser(command);

        // then
        verify(userService).create(any());
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).isInstanceOf(UserJson.class);
        assertThat(response.getBody().getId()).isEqualTo(1L);
    }

    @Test
    void  itShouldGetUserById() {
        // given
        Long userId = 1L;

        // when
        when(userService.findById(userId)).thenReturn(getAnyUser().setId(userId));
        ResponseEntity<Object> expected = underTest.getUserById(userId);

        // then
        verify(userService).findById(userId);
        assertThat(expected.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void  itShouldGetNotFoundStatusIfUserDoesNotExist() {
        // when
        when(userService.findById(any())).thenThrow(EntityNotFoundException.class);
        ResponseEntity<Object> expected = underTest.getUserById(any());

        // then
        assertThat(expected.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void isShouldGetAListOfUsers() {
        // given
        Pageable pageable = Pageable.unpaged();
        UserQuery userQuery = new UserQuery();

        // when
        when(userService.fetchPaged(pageable, userQuery)).thenReturn(getAnyUserPage());
        ResponseEntity<Page<UserJson>> expected = underTest.getUsersPage(userQuery, pageable);

        // then
        verify(userService).fetchPaged(pageable, userQuery);
        assertThat(expected.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(expected.getBody()).isNotNull();
        assertThat(expected.getBody().getTotalElements()).isEqualTo(getAnyUserPage().getTotalElements());
    }
}
