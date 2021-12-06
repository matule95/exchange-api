package mz.co.checkmob.api.jobs.service;

import lombok.RequiredArgsConstructor;
import mz.co.checkmob.api.jobs.domain.RequestExecutor;
import mz.co.checkmob.api.jobs.domain.TimeUnity;
import mz.co.checkmob.api.jobs.persistence.RequestExecutorRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class RequestExecutorJob implements SchedulingConfigurer {
    private final RequestExecutorRepository repository;
    private Executor executor;

    public void execute() {
        process();
    }


    private void executeRequest(RequestExecutor executor) {
        nextScheduledDate(executor);
    }

    private void nextScheduledDate(RequestExecutor executor) {
        LocalDateTime atualDate = executor.getExecuteAt();
        executor.setExecuteAt(atualDate.plusMinutes(executor.getMinutes()));
        repository.save(executor);
    }

    private void process() {
        try {
            List<RequestExecutor> requestExecutors = repository.findByExecuteAtLessThanEqual(LocalDateTime.now());
            if (!requestExecutors.isEmpty()) {
                requestExecutors.forEach(this::executeRequest);
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(executor);
        scheduledTaskRegistrar.addTriggerTask(
                new Runnable() {
                    @Override
                    public void run() {
                        execute();
                    }
                },
                new Trigger() {
                    @Override
                    public Date nextExecutionTime(TriggerContext context) {
                        CronTrigger crontrigger = new CronTrigger("* * * * * *");
                        return crontrigger.nextExecutionTime(context);
                    }
                }
        );
    }
}
