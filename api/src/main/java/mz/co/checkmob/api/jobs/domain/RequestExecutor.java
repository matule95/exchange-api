package mz.co.checkmob.api.jobs.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mz.co.checkmob.api.connections.domain.Connection;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "request_executor")
@NoArgsConstructor
public class RequestExecutor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private RequestFrequency frequency;
    @Setter
    private LocalDateTime executeAt;
    @Getter
    private Integer every;
    @Getter
    private TimeUnity unity;
    @OneToOne(mappedBy = "executor",fetch = FetchType.EAGER)
    private Connection connection;

    public RequestExecutor(RequestFrequency frequency, Integer every, TimeUnity unity) {
        this.frequency = frequency;
        this.every = every;
        this.unity = unity;
    }
}
