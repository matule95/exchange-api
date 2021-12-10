package mz.co.exchange.api.history.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import mz.co.exchange.api.rate.domain.Rate;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "rate_history")
@Data
@NoArgsConstructor
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Rate rate;
    private Float sale;
    private Float purchase;
    @CreationTimestamp
    private LocalDateTime createdAt;

    public History(Rate rate) {
        this.rate = rate;
        this.sale = rate.getSale();
        this.purchase = rate.getPurchase();
    }
}
