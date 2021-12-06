package mz.co.checkmob.api.jobs.domain;

import lombok.Data;

@Data
public class RequestExecutorCommand {
    private RequestFrequency frequency;
    private Integer every;
    private TimeUnity unity;
}
