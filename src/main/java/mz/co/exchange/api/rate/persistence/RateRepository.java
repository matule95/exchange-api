package mz.co.exchange.api.rate.persistence;

import mz.co.exchange.api.currency.domain.Currency;
import mz.co.exchange.api.rate.domain.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface RateRepository extends JpaRepository<Rate, Long>, JpaSpecificationExecutor<Rate> {
    Rate findByBaseCurrencyIdAndTargetCurrencyId(Long baseCurrencyId,Long targetCurrencyId);
    List<Rate> findByBaseCurrencyId(Long baseCurrencyId);
}
