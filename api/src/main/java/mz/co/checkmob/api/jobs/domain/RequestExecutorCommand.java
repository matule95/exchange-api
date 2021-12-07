package mz.co.checkmob.api.jobs.domain;

import lombok.Data;

@Data
public class RequestExecutorCommand {
    private RequestFrequency frequency;
    private Long every;
    private TimeUnity unity;
}
