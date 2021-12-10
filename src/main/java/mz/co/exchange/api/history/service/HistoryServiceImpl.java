package mz.co.exchange.api.history.service;

import lombok.RequiredArgsConstructor;
import mz.co.exchange.api.history.domain.History;
import mz.co.exchange.api.history.domain.query.HistoryQuery;
import mz.co.exchange.api.history.domain.query.HistoryQueryGateway;
import mz.co.exchange.api.history.persistence.HistoryRepository;
import mz.co.exchange.api.history.presentation.HistoryJson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {
    private final HistoryQueryGateway queryGateway;
    private final HistoryRepository repository;
    @Override
    public boolean addHistory(History history) {
        return repository.save(history).getId() != null;
    }

    @Override
    public Page<HistoryJson> getRateHistory(Pageable pageable, HistoryQuery historyQuery, Long rateId) {
        return queryGateway.executeQuery(pageable,historyQuery,rateId);
    }
}
