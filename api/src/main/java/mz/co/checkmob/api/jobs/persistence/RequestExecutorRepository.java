package mz.co.checkmob.api.jobs.persistence;

import mz.co.checkmob.api.jobs.domain.RequestExecutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDateTime;
import java.util.List;

public interface RequestExecutorRepository extends JpaRepository<RequestExecutor, Long>{
    List<RequestExecutor> findByExecuteAtLessThanEqual(LocalDateTime executeAt);
}
