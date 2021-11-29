package mz.co.checkmob.api.connections.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NoAuthMock {
    private String id;
    private LocalDateTime createdAt;
    private String name;
    private String email;
}
