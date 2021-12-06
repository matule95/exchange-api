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
        switch (executor.getFrequency()) {
            case DAILY:
                if (executor.getUnity().equals(TimeUnity.MINUTE))
                    executor.setExecuteAt(atualDate.plusMinutes(executor.getEvery()));
                else
                    executor.setExecuteAt(atualDate.plusHours(executor.getEvery()));
                break;
            case WEEKLY:
                executor.setExecuteAt(atualDate.plusWeeks(1L));
                break;
            case MONTHLY:
                executor.setExecuteAt(atualDate.plusMonths(1L));
                break;
            case ANNUALLY:
                executor.setExecuteAt(atualDate.plusYears(1L));
                break;
            case QUARTERLY:
                executor.setExecuteAt(atualDate.plusWeeks(3L));
                break;
            case SEMIANNUALLY:
                executor.setExecuteAt(atualDate.plusWeeks(6L));
                break;

        }
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
