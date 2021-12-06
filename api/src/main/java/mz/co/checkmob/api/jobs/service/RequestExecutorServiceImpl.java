package mz.co.checkmob.api.jobs.service;

import lombok.RequiredArgsConstructor;
import mz.co.checkmob.api.jobs.domain.RequestExecutor;
import mz.co.checkmob.api.jobs.domain.TimeUnity;
import mz.co.checkmob.api.jobs.persistence.RequestExecutorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RequestExecutorServiceImpl implements RequestExecutorService{
    private final RequestExecutorRepository requestExecutorRepository;

    @Override
    public RequestExecutor create(RequestExecutor executor) {
        if(executor.getUnity().equals(TimeUnity.MINUTE))
            executor.setExecuteAt(LocalDateTime.now().plusMinutes(executor.getEvery()));
        else
            executor.setExecuteAt(LocalDateTime.now().plusHours(executor.getEvery()));
        return requestExecutorRepository.save(executor);
    }
}
