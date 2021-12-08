package mz.co.checkmob.api.jobs.service;

import mz.co.checkmob.api.jobs.domain.RequestExecutor;
import mz.co.checkmob.api.jobs.domain.RequestExecutorCommand;

public interface RequestExecutorService {
    RequestExecutor create(RequestExecutorCommand command);
    RequestExecutor update(RequestExecutor executor);
}
