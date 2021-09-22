package mz.co.checkmob.api.authorization.domain;

import lombok.*;
import mz.co.checkmob.api.utils.JsonObjectConverter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Data
@Table(name = "authorizations")
@SQLDelete(sql = "UPDATE authorizations SET deleted_at = now() WHERE id=?")
@Where(clause = "deleted_at is null")
@NoArgsConstructor
@AllArgsConstructor
public class Authorization{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private AuthorizationType type;

    @Convert(converter = JsonObjectConverter.class)
    private Map<String, Object> authJson;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
    
    private LocalDateTime deletedAt;

}