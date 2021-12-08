package mz.co.checkmob.api.jobs.service;

import lombok.RequiredArgsConstructor;
import mz.co.checkmob.api.jobs.domain.*;
import mz.co.checkmob.api.jobs.persistence.RequestExecutorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
    public RequestExecutor update(RequestExecutor executor) {
        return null;
    }
}
