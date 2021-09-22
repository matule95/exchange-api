package mz.co.checkmob.api.authorization;
import mz.co.checkmob.api.AbstractTest;
import mz.co.checkmob.api.authorization.domain.Authorization;
import mz.co.checkmob.api.authorization.domain.AuthorizationCommand;
import mz.co.checkmob.api.authorization.domain.AuthorizationMapper;
import mz.co.checkmob.api.authorization.persistence.AuthorizationRepository;
import mz.co.checkmob.api.authorization.service.AuthorizationService;
import mz.co.checkmob.api.authorization.service.AuthorizationServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AuthorizationServiceTests extends AbstractTest {
    @Autowired
    private AuthorizationService underTest;

    @Mock
    private AuthorizationRepository repository;

    @BeforeEach
    void setUp() {
        underTest = new AuthorizationServiceImpl(repository);
    }

    @Test
    void shouldSaveAuthorization(){
        //given
        AuthorizationCommand command = anyAuthorizationCommand();
        Authorization authorization = AuthorizationMapper.INSTANCE.mapToModel(command);
        //when
        when(repository.save(authorization)).thenReturn(authorization);
        //then
        Assertions.assertNotNull(underTest.create(command));
    }

    @Test
    void shouldUpdateAuthorization(){
        //given
        AuthorizationCommand command = anyAuthorizationCommand();
        Authorization authorization = AuthorizationMapper.INSTANCE.mapToModel(command);

        //when
        when(repository.findById(authorization.getId())).thenReturn(Optional.of(authorization));
        when(repository.save(authorization)).thenReturn(authorization);

        //then
        Assertions.assertNotNull(underTest.update(authorization.getId(), command));
    }

    @Test
    void shouldFindById(){
        //given
        Authorization authorization = anyAuthorization();

        //when
        when(repository.findById(authorization.getId())).thenReturn(Optional.of(authorization));

        //then
        Assertions.assertNotNull(underTest.findById(authorization.getId()));
    }

    @Test
    void shouldDeleteById(){
        //given
        Authorization authorization = anyAuthorization();

        // when
        when(repository.findById(authorization.getId())).thenReturn(Optional.of(authorization));
        underTest.deleteById(authorization.getId());

        // then
        verify(repository).findById(authorization.getId());
        verify(repository).deleteById(authorization.getId());
    }
}
