package mz.co.checkmob.api.connections.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import mz.co.checkmob.api.connections.domain.*;
import mz.co.checkmob.api.connections.persistence.ConnectionRepository;
import mz.co.checkmob.api.connections.persistence.ParamRepository;
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
import java.util.*;

@Service
@RequiredArgsConstructor
public class ConnectionServiceImpl implements ConnectionService {
    private final ConnectionRepository connectionRepository;
    private final ParamRepository paramRepository;
    private final EndpointService endpointService;

    @Override
    @Transactional
    public Connection create(CreateConnectionCommand command) {

        Connection connection = ConnectionMapper.INSTANCE.mapToModel(command);

        Endpoint endpointA = endpointService.findById(command.getFromThirdParty());
        connection.setFromThirdParty(endpointA);
        connection.setFromUrl(endpointA.getUrl()+command.getFromUrl());

        Endpoint endpointB = endpointService.findById(command.getToThirdParty());
        connection.setToThirdParty(endpointB);
        connection.setToUrl(endpointB.getUrl()+command.getToUrl());

        Object noAuthMock = API.NO_AUTH.get(endpointA.getUrl()+command.getFromUrl(), Object.class);
        Map<String, Object> map = new ObjectMapper().convertValue(noAuthMock,Map.class);
        MultiValueMap<String,Object> params = new LinkedMultiValueMap<>();

        OperationType.operate(command.getParams(), map, params, command.getOperationType());

        Object object = API.NO_AUTH.post(endpointB.getUrl()+command.getToUrl(),params, Object.class);

        return save(connection);
    }

    private Connection save(Connection connection){
        Connection con = connectionRepository.save(connection);

        con.getParams().forEach(param -> {
            param.setConnection(con);
            paramRepository.save(param);
        });

        return connectionRepository.save(con);
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
