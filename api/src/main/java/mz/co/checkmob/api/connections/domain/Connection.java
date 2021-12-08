package mz.co.checkmob.api.connections.domain;

import lombok.*;
import mz.co.checkmob.api.endpoint.domain.Endpoint;
import mz.co.checkmob.api.jobs.domain.RequestExecutor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "connections")
@SQLDelete(sql = "UPDATE connections SET deleted_at = now() WHERE id=?")
@Where(clause = "deleted_at is null")
@AllArgsConstructor
@NoArgsConstructor
public class Connection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    private Endpoint fromThirdParty;

    private String fromUrl;
    private RequestType fromRequestType;


    @ManyToOne
    private Endpoint toThirdParty;

    private String toUrl;
    private RequestType toRequestType;


    @OneToMany(mappedBy = "connection", fetch = FetchType.EAGER)
    private List<Param> params;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    @Column(name = "name")
    private String name;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private RequestExecutor requestExecutor;

}
