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
    @Getter
    private Long id;
    @Getter
    @Setter
    private RequestFrequency frequency;
    @Setter
    @Getter
    private LocalDateTime executeAt;
    @Getter
    @Setter
    private Integer every;
    @Getter
    @Setter
    private TimeUnity unity;
    @OneToOne(mappedBy = "requestExecutor",fetch = FetchType.EAGER)
    private Connection connection;

    public RequestExecutor(RequestFrequency frequency, Integer every, TimeUnity unity) {
        this.frequency = frequency;
        this.every = every;
        this.unity = unity;
    }
}
