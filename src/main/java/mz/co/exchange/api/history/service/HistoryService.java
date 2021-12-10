package mz.co.exchange.api.history.service;

import mz.co.exchange.api.history.domain.History;
import mz.co.exchange.api.history.domain.query.HistoryQuery;
import mz.co.exchange.api.history.presentation.HistoryJson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HistoryService {
    boolean addHistory(History history);
    Page<HistoryJson> getRateHistory(Pageable pageable, HistoryQuery historyQuery, Long rateId);
}
