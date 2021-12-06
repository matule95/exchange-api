package mz.co.checkmob.api.jobs.service;

import lombok.RequiredArgsConstructor;
import mz.co.checkmob.api.jobs.domain.RequestExecutor;
import mz.co.checkmob.api.jobs.domain.RequestExecutorCommand;
import mz.co.checkmob.api.jobs.domain.RequestExecutorMapper;
import mz.co.checkmob.api.jobs.domain.TimeUnity;
import mz.co.checkmob.api.jobs.persistence.RequestExecutorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RequestExecutorServiceImpl implements RequestExecutorService{
    private final RequestExecutorRepository requestExecutorRepository;
    private final RequestExecutorMapper MAPPER = RequestExecutorMapper.INSTANCE;

    @Override
    public RequestExecutor create(RequestExecutorCommand command) {
        RequestExecutor executor = MAPPER.mapToModel(command);
        if(executor.getUnity().equals(TimeUnity.MINUTE))
            executor.setExecuteAt(LocalDateTime.now().plusMinutes(executor.getEvery()));
        else
            executor.setExecuteAt(LocalDateTime.now().plusHours(executor.getEvery()));
        return requestExecutorRepository.save(executor);
    }

    @Override
    public RequestExecutor update(RequestExecutor executor) {
        return null;
    }
}
