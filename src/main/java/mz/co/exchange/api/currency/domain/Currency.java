package mz.co.exchange.api.currency.domain;

import lombok.Data;
import mz.co.exchange.api.provider.domain.Provider;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "currencies")
@SQLDelete(sql = "UPDATE currencies SET deleted_at = now() WHERE id=?")
@Where(clause = "deleted_at is null")
@Data
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String isoCode;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Provider provider;
}
