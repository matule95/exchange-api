package mz.co.checkmob.api.connections.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import mz.co.checkmob.api.connections.domain.*;
import mz.co.checkmob.api.connections.domain.CreateConnectionCommand;
import mz.co.checkmob.api.connections.domain.Connection;
import mz.co.checkmob.api.connections.domain.ConnectionMapper;
import mz.co.checkmob.api.connections.domain.query.ConnectionQuery;
import mz.co.checkmob.api.connections.domain.query.ConnectionSpecification;
import mz.co.checkmob.api.connections.persistence.ConnectionRepository;
import mz.co.checkmob.api.connections.persistence.ParamRepository;
import mz.co.checkmob.api.connections.presentation.ConnectionJson;
import mz.co.checkmob.api.core.utils.API;
import mz.co.checkmob.api.endpoint.domain.Endpoint;
import mz.co.checkmob.api.endpoint.service.EndpointService;
import mz.co.checkmob.api.jobs.service.RequestExecutorService;
import org.codehaus.groovy.util.ListHashMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConnectionServiceImpl implements ConnectionService {
    private final ConnectionRepository connectionRepository;
    private final ParamRepository paramRepository;
    private final EndpointService endpointService;
    private final ConnectionSpecification connectionSpecification;
    private final RequestExecutorService requestExecutorService;

    @Override
    @Transactional
    public Connection create(CreateConnectionCommand command) {

        Connection connection = ConnectionMapper.INSTANCE.mapToModel(command);
        connection.setRequestExecutor(requestExecutorService.create(command.getFrequency()));
        Endpoint endpointA = endpointService.findById(command.getFromThirdParty());
        connection.setFromThirdParty(endpointA);
        connection.setFromUrl(endpointA.getUrl()+command.getFromUrl());

        Endpoint endpointB = endpointService.findById(command.getToThirdParty());
        connection.setToThirdParty(endpointB);
        connection.setToUrl(endpointB.getUrl()+command.getToUrl());

        Object noAuthMock = API.NO_AUTH.request(endpointA.getUrl()+command.getFromUrl(),
                command.getFromRequestType(), null,Object.class);

        if(noAuthMock instanceof Collection){
            operateSingle(noAuthMock, command, endpointB);
        }else{
            operateCollection(noAuthMock, command, endpointB);
        }

        return save(connection);
    }

    private void operateSingle(Object noAuthMock, CreateConnectionCommand command, Endpoint endpointB){
        ObjectMapper objectMapper = new ObjectMapper();

        List<Map<String, Object>> map = (List<Map<String, Object>>) ((List) noAuthMock).parallelStream()
                .map(e-> objectMapper.convertValue(e,Map.class)).collect(Collectors.toList());

        List<Map<String,Object>> params = new ArrayList<>();

        for(Map<String,Object> m : map) {
            Map<String,Object> p = new ListHashMap<>();
            OperationType.operate(command.getParams(), m, p);
            params.add(p);
        }

        params.parallelStream().forEach(param -> sendRequest(endpointB.getUrl()+command.getToUrl(), command.getToRequestType(), param));
    }

    private void operateCollection(Object noAuthMock, CreateConnectionCommand command, Endpoint endpointB){
        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> map = objectMapper.convertValue(noAuthMock,Map.class);

        Map<String,Object> params = new LinkedHashMap<>();

        OperationType.operate(command.getParams(), map, params);

        sendRequest(endpointB.getUrl()+command.getToUrl(), command.getToRequestType(), params);
    }

    private void sendRequest(String url, RequestType requestType, Object params){
        try{
            API.NO_AUTH.request(url,
                    requestType, params, Object.class);
        }catch(WebClientResponseException e){
            System.out.println(e.getMessage());
        }
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
    public Page<ConnectionJson> findAll(Pageable pageable,ConnectionQuery connectionQuery) {
        return ConnectionMapper.INSTANCE.mapToJson(
                connectionSpecification.executeQuery(connectionQuery,pageable));
    }

    @Override
    public void deleteById(Long id) {
        Connection connection = findById(id);
        connectionRepository.deleteById(connection.getId());
    }

    @Override
    public long countAllConnections() {
        return connectionRepository.count();
    }

}
