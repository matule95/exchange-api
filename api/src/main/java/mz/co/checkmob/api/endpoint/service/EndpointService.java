package mz.co.checkmob.api.endpoint.service;

import mz.co.checkmob.api.endpoint.domain.CreateEndpointCommand;
import mz.co.checkmob.api.endpoint.domain.Endpoint;
import mz.co.checkmob.api.endpoint.domain.UpdateEndpointCommand;
import mz.co.checkmob.api.endpoint.domain.query.EndpointQuery;
import mz.co.checkmob.api.endpoint.presentation.EndpointJson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EndpointService {
    Endpoint create(CreateEndpointCommand command);
    Endpoint update(UpdateEndpointCommand command);
    Page<EndpointJson> findAllByCompanyId(Long id, Pageable pageable);
    Endpoint findById(Long id);
    Page<EndpointJson> findAll(Pageable pageable, EndpointQuery endpointQuery);
    void deleteById(Long id);
    long countAllEndpoints();
}
