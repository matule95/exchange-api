package mz.co.checkmob.api.jobs.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mz.co.checkmob.api.connections.domain.Connection;
import mz.co.checkmob.api.jobs.presentation.RequestExecutorJson;

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
    private Long minutes;
    @Setter
    @Getter
    private LocalDateTime executeAt;
    @Getter
    @OneToOne(mappedBy = "requestExecutor",fetch = FetchType.EAGER)
    private Connection connection;

    public RequestExecutorJson jsonResponse(){
        if(this.minutes/RequestFrequency.DAILY.getMinutes() < 1){
            if(this.minutes < 60L){
                return new RequestExecutorJson(RequestFrequency.DAILY,this.executeAt,this.minutes, TimeUnity.MINUTE);
            }else{
                return new RequestExecutorJson(RequestFrequency.DAILY,this.executeAt,this.minutes/60L, TimeUnity.HOUR);
            }
        }else if(this.minutes/RequestFrequency.DAILY.getMinutes() == 1){
            return new RequestExecutorJson(RequestFrequency.DAILY,this.executeAt);
        }else if(this.minutes/RequestFrequency.WEEKLY.getMinutes() == 1){
            return new RequestExecutorJson(RequestFrequency.WEEKLY,this.executeAt);
        }else if(this.minutes/RequestFrequency.MONTHLY.getMinutes() == 1){
            return new RequestExecutorJson(RequestFrequency.MONTHLY,this.executeAt);
        }else if(this.minutes/RequestFrequency.QUARTERLY.getMinutes() == 1){
            return new RequestExecutorJson(RequestFrequency.QUARTERLY,this.executeAt);
        }else if(this.minutes/RequestFrequency.SEMIANNUALLY.getMinutes() == 1){
            return new RequestExecutorJson(RequestFrequency.SEMIANNUALLY,this.executeAt);
        }else if(this.minutes/RequestFrequency.ANNUALLY.getMinutes() == 1){
            return new RequestExecutorJson(RequestFrequency.ANNUALLY,this.executeAt);
        }
        return new RequestExecutorJson();
    }
}
