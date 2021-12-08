package mz.co.checkmob.api.jobs.presentation;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import mz.co.checkmob.api.jobs.domain.RequestFrequency;
import mz.co.checkmob.api.jobs.domain.TimeUnity;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestExecutorJson {
    private Long id;
    private RequestFrequency frequency;
    private LocalDateTime executeAt;
    private Long every;
    private TimeUnity unity;

    public RequestExecutorJson(RequestFrequency frequency, LocalDateTime executeAt) {
        this.frequency = frequency;
        this.executeAt = executeAt;
    }

    public RequestExecutorJson(RequestFrequency frequency, LocalDateTime executeAt, Long every, TimeUnity unity) {
        this.frequency = frequency;
        this.executeAt = executeAt;
        this.every = every;
        this.unity = unity;
    }
}
