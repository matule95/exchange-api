package mz.co.checkmob.api.connections.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mz.co.checkmob.api.endpoint.domain.Endpoint;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "connections")
@SQLDelete(sql = "UPDATE connections SET deleted_at = now() WHERE id=?")
@Where(clause = "deleted_at is null")
@AllArgsConstructor
@NoArgsConstructor
public class Connection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Long fromThirdParty;
    private String fromUrl;
    private RequestType fromRequestType;


    private Long toThirdParty;
    private String toUrl;
    private RequestType toRequestType;


    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;
}
