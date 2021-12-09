package mz.co.checkmob.api.jobs.service;

import lombok.RequiredArgsConstructor;
import mz.co.checkmob.api.connections.domain.Connection;
import mz.co.checkmob.api.connections.domain.RequestType;
import mz.co.checkmob.api.endpoint.request.NoAuthRequest;
import mz.co.checkmob.api.endpoint.domain.Endpoint;
import mz.co.checkmob.api.jobs.domain.*;
import mz.co.checkmob.api.jobs.persistence.RequestExecutorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RequestExecutorServiceImpl implements RequestExecutorService {
    private final RequestExecutorRepository requestExecutorRepository;

    @Override
    public RequestExecutor create(RequestExecutorCommand command) {
        RequestExecutor executor = new RequestExecutor();
        if (command.getFrequency().equals(RequestFrequency.DAILY)) {
            if (command.getEvery() == null) {
                executor.setExecuteAt(LocalDateTime.now().plusMinutes(command.getFrequency().getMinutes()));
                executor.setMinutes(command.getFrequency().getMinutes());
            } else {
                if (command.getUnity().equals(TimeUnity.MINUTE)) {
                    executor.setExecuteAt(LocalDateTime.now().plusMinutes(command.getEvery()));
                    executor.setMinutes(command.getEvery());
                } else {
                    executor.setExecuteAt(LocalDateTime.now().plusHours(command.getEvery()));
                    executor.setMinutes(command.getEvery() * 60);
                }
            }
        }else{
            executor.setMinutes(command.getFrequency().getMinutes());
            executor.setExecuteAt(LocalDateTime.now().plusMinutes(executor.getMinutes()));
        }
        return requestExecutorRepository.save(executor);
    }

    @Override
    public void execute(Connection connection) {
        fromRequestExecution(connection.getFromUrl(), connection.getFromThirdParty());
    }
    private Map<String,Object> doRequest(RequestType type,String URL,Endpoint endpoint){
        return new NoAuthRequest().authenticate(type, URL, endpoint);
    }

    private Map<String, Object> fromRequestExecution(String fromURL, Endpoint endpoint) {
        return doRequest(RequestType.GET,fromURL,endpoint);
    }

    @Override
    public RequestExecutor update(RequestExecutor executor) {
        return null;
    }
}
