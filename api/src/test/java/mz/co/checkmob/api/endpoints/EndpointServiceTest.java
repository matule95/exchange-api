package mz.co.checkmob.api.endpoints;

import mz.co.checkmob.api.AbstractTest;
import mz.co.checkmob.api.endpoint.domain.CreateEndpointCommand;
import mz.co.checkmob.api.endpoint.domain.Endpoint;
import mz.co.checkmob.api.endpoint.domain.EndpointMapper;
import mz.co.checkmob.api.endpoint.domain.UpdateEndpointCommand;
import mz.co.checkmob.api.endpoint.persistence.EndpointRepository;
import mz.co.checkmob.api.endpoint.service.EndpointService;
import mz.co.checkmob.api.endpoint.service.EndpointServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EndpointServiceTest extends AbstractTest {
    @Autowired
    private EndpointService underTest;

    @Mock
    private EndpointRepository repository;

    @BeforeEach
    void setUp() {
        underTest = new EndpointServiceImpl(repository);
    }
    @Test
    void shouldSaveEndpoint(){
        //given
        CreateEndpointCommand command = anyCreateEndpointCommand();
        Endpoint endpoint = EndpointMapper.INSTANCE.mapToModel(command);
        //when
        when(repository.save(endpoint)).thenReturn(endpoint);
        //then
        Assertions.assertNotNull(underTest.create(command));
    }

    @Test
    void shouldUpdateEndpoint(){
        //given
        UpdateEndpointCommand command = anyUpdateEndpointCommand();
        Endpoint endpoint = anyEndpoint();
        //when
        when(repository.findById(command.getId())).thenReturn(Optional.of(endpoint));
        when(repository.save(endpoint)).thenReturn(endpoint);

        //then
        Assertions.assertNotNull(underTest.update(command));
    }

    @Test
    void shouldFindById(){
        //given
        Endpoint endpoint = anyEndpoint();

        //when
        when(repository.findById(endpoint.getId())).thenReturn(Optional.of(endpoint));

        //then
        Assertions.assertNotNull(underTest.findById(endpoint.getId()));
    }

    @Test
    void shouldDeleteById(){
        //given
        Endpoint endpoint = anyEndpoint();

        // when
        when(repository.findById(endpoint.getId())).thenReturn(Optional.of(endpoint));
        underTest.deleteById(endpoint.getId());

        // then
        verify(repository).findById(endpoint.getId());
        verify(repository).deleteById(endpoint.getId());
    }
}
