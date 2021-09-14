package mz.co.checkmob.api.endpoint.service;

import mz.co.checkmob.api.endpoint.domain.CreateEndpointCommand;
import mz.co.checkmob.api.endpoint.domain.Endpoint;
import mz.co.checkmob.api.endpoint.domain.UpdateEndpointCommand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EndpointService {
    Endpoint create(CreateEndpointCommand command);
    Endpoint update(UpdateEndpointCommand command);
    Endpoint findById(Long id);
    Page<Endpoint> findAll(Pageable pageable);
    void deleteById(Long id);
}
