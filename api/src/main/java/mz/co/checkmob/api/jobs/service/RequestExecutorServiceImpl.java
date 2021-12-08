package mz.co.checkmob.api.jobs.service;

import lombok.RequiredArgsConstructor;
import mz.co.checkmob.api.connections.domain.Connection;
import mz.co.checkmob.api.core.utils.ApiService;
import mz.co.checkmob.api.endpoint.domain.Endpoint;
import mz.co.checkmob.api.jobs.domain.*;
import mz.co.checkmob.api.jobs.persistence.RequestExecutorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
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

    private Map<String, Object> fromRequestExecution(String fromURL, Endpoint endpoint) {
        Map<String, Object> result = new LinkedHashMap<>();
        Map<String, Object> authentication = endpoint.authenticate();
        if (authentication != null) {
            StringBuilder headerValue = new StringBuilder();
            String prefix = endpoint.getDataReader().get("prefix").toString();
            String response = endpoint.getDataReader().get("response").toString();
            String token = authentication.get(response).toString();
            if (prefix.isEmpty()) {
                headerValue.append(token);
            } else {
                headerValue.append(prefix);
                headerValue.append(" ");
                headerValue.append(token);
            }
            result = ApiService.get(fromURL, endpoint.getDataReader().get("header").toString(), headerValue.toString(), Map.class);
        }else{
            result = ApiService.get(fromURL,Map.class);
        }
        return result;
    }

    @Override
    public RequestExecutor update(RequestExecutor executor) {
        return null;
    }
}
