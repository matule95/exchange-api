package mz.co.exchange.api.provider.domain;

import lombok.Data;
import mz.co.exchange.api.currency.domain.Currency;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "providers")
@SQLDelete(sql = "UPDATE providers SET deleted_at = now() WHERE id=?")
@Where(clause = "deleted_at is null")
@Data
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    @OneToMany(mappedBy = "provider")
    private List<Currency> currencies;
}
