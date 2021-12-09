package mz.co.exchange.api.rate.domain;

import lombok.Data;
import mz.co.exchange.api.currency.domain.Currency;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "rates")
@SQLDelete(sql = "UPDATE rates SET deleted_at = now() WHERE id=?")
@Where(clause = "deleted_at is null")
@Data
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Currency baseCurrency;
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Currency targetCurrency;
    private Float purchase;
    private Float sale;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
