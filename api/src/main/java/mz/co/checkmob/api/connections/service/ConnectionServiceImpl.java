package mz.co.checkmob.api.connections.service;

import lombok.RequiredArgsConstructor;
import mz.co.checkmob.api.connections.domain.CreateConnectionCommand;
import mz.co.checkmob.api.connections.domain.Connection;
import mz.co.checkmob.api.connections.domain.ConnectionMapper;
import mz.co.checkmob.api.connections.domain.NoAuthMock;
import mz.co.checkmob.api.connections.persistence.ConnectionRepository;
import mz.co.checkmob.api.connections.presentation.ConnectionJson;
import mz.co.checkmob.api.core.utils.API;
import mz.co.checkmob.api.endpoint.domain.Endpoint;
import mz.co.checkmob.api.endpoint.service.EndpointService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class ConnectionServiceImpl implements ConnectionService {
    private final ConnectionRepository connectionRepository;
    private final EndpointService endpointService;

    @Override
    @Transactional
    public Connection create(CreateConnectionCommand command) {
        Connection connection = ConnectionMapper.INSTANCE.mapToModel(command);
        Endpoint endpointA = endpointService.findById(command.getFromThirdParty());
        connection.setFromUrl(endpointA.getUrl()+command.getFromUrl());
        Endpoint endpointB = endpointService.findById(command.getToThirdParty());
        connection.setToUrl(endpointB.getUrl()+command.getToUrl());
        NoAuthMock noAuthMock = API.NO_AUTH.get(endpointA.getUrl()+command.getFromUrl(), NoAuthMock.class);
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("createdAt","2021-11-26T10:28:34.646");
        params.add("name",noAuthMock.getName());
        params.add("email",noAuthMock.getEmail());
        API.NO_AUTH.post(endpointB.getUrl()+command.getToUrl(),params,NoAuthMock.class);
        return connectionRepository.save(connection);
    }


    @Override
    public Connection findById(Long id) {
        return connectionRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Page<ConnectionJson> findAll(Pageable pageable) {
        return ConnectionMapper.INSTANCE.mapToJson(connectionRepository.findAll(pageable));
    }

    @Override
    public void deleteById(Long id) {
        Connection connection = findById(id);
        connectionRepository.deleteById(connection.getId());
    }
}
