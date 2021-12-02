package mz.co.checkmob.api.connections.service;

import mz.co.checkmob.api.connections.domain.Connection;
import mz.co.checkmob.api.connections.domain.CreateConnectionCommand;
import mz.co.checkmob.api.connections.domain.query.ConnectionQuery;
import mz.co.checkmob.api.connections.presentation.ConnectionJson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ConnectionService {
    Connection create(CreateConnectionCommand command);
    Connection findById(Long id);
    Page<ConnectionJson> findAll(Pageable pageable, ConnectionQuery connectionQuery);
    void deleteById(Long id);
    long countAllConnections();
}
