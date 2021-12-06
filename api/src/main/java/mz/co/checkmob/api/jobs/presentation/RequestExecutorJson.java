package mz.co.checkmob.api.jobs.presentation;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import mz.co.checkmob.api.jobs.domain.RequestFrequency;
import mz.co.checkmob.api.jobs.domain.TimeUnity;

import java.time.LocalDateTime;

@Data
public class RequestExecutorJson {
    private Long id;
    private RequestFrequency frequency;
    private LocalDateTime executeAt;
    private Integer every;
    private TimeUnity unity;
}
