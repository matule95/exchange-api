package mz.co.exchange.api.history.domain.query;

import lombok.RequiredArgsConstructor;
import mz.co.exchange.api.history.domain.History;
import mz.co.exchange.api.history.domain.HistoryMapper;
import mz.co.exchange.api.history.persistence.HistoryRepository;
import mz.co.exchange.api.history.presentation.HistoryJson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class HistoryQueryGateway {
    private final HistoryRepository repository;
    public Page<HistoryJson> executeQuery(Pageable pageable, HistoryQuery historyQuery, Long rateId){
        return HistoryMapper.INSTANCE.mapToJson(repository.findAll(toSpecification(historyQuery,rateId), pageable));
    }

    public Specification<History> toSpecification(HistoryQuery historyQuery, Long rateId){
        return all()
                .and(findByRateId(rateId))
                .and(findByCreatedAtBetween(historyQuery.getStartDate(),historyQuery.getEndDate()));
    }

    public static Specification<History> all(){
        return (root, criteriaQuery, criteriaBuilder)-> criteriaBuilder.and();
    }

    public static Specification<History> findByRateId(Long rateId) {
        if (rateId == null) return null;
        return (Specification<History>) (root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("rate").get("id"), rateId);
        };
    } public static Specification<History> findByCreatedAtBetween(LocalDate startDate, LocalDate endDate) {
        if (startDate == null && endDate == null) return null;
        if(startDate != null){
            return (Specification<History>) (root, criteriaQuery, criteriaBuilder) -> {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), LocalDateTime.from(startDate.atStartOfDay()));
            };
        }
        if(endDate != null){
            return (Specification<History>) (root, criteriaQuery, criteriaBuilder) -> {
                return criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"), LocalDateTime.from(endDate.atTime(23,59)));
            };
        }
        return (Specification<History>) (root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.between(root.get("createdAt"), LocalDateTime.from(startDate.atStartOfDay()), LocalDateTime.from(endDate.atTime(23,59)));
        };
    }



}
