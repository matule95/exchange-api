package mz.co.checkmob.api.connections.persistence;

import mz.co.checkmob.api.connections.domain.Connection;
import mz.co.checkmob.api.connections.domain.query.ConnectionQuery;
import mz.co.checkmob.api.connections.presentation.ConnectionJson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ConnectionRepository extends JpaRepository<Connection, Long>, JpaSpecificationExecutor<Connection> {
    Page<Connection> findAll(Specification<Connection> specification, Pageable pageable);
}
