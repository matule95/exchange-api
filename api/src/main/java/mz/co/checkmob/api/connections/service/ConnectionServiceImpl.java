package mz.co.checkmob.api.connections.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import mz.co.checkmob.api.connections.domain.*;
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
import java.util.List;
import java.util.Map;

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
        Object noAuthMock = API.NO_AUTH.get(endpointA.getUrl()+command.getFromUrl(), Object.class);
        Map<String, Object> map = new ObjectMapper().convertValue(noAuthMock,Map.class);
        MultiValueMap<String,Object> params = new LinkedMultiValueMap<>();

        command.getParams().parallelStream().forEach(param -> {
            params.add(param.getTo(), map.get(param.getFrom()));
        });
        map.forEach((k, v) -> {
            if(!contains(command.getParams(), k)){
                params.add(k, v);
            }
        });


//        params.add("createdAt","2021-11-26T10:28:34.646");
//        params.add("name",noAuthMock.getName());
//        params.add("email",noAuthMock.getEmail());
        Object object = API.NO_AUTH.post(endpointB.getUrl()+command.getToUrl(),params,MultiValueMap.class);



        return connectionRepository.save(connection);
    }

    private boolean contains(List<Param> params, String key){
       return params.parallelStream().anyMatch(param -> param.getFrom().equals(key));
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
