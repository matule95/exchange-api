package mz.co.checkmob.api.connections.persistence;

import mz.co.checkmob.api.connections.domain.Param;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParamRepository extends JpaRepository<Param, Long> {
}
