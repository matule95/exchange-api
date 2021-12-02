package mz.co.checkmob.api.endpoint.persistence;

import mz.co.checkmob.api.endpoint.domain.Endpoint;
import mz.co.checkmob.api.endpoint.domain.query.EndpointQuery;
import mz.co.checkmob.api.endpoint.presentation.EndpointJson;
import mz.co.checkmob.api.utils.PageJson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface EndpointRepository extends JpaRepository<Endpoint, Long>, JpaSpecificationExecutor<Endpoint> {
    Page<Endpoint> findAll(Specification<Endpoint> specification, Pageable pageable);


    Page<EndpointJson> findAllByCompanyId(Long id, Pageable pageable);

}
