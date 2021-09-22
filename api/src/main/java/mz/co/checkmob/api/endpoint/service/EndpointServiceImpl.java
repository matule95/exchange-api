package mz.co.checkmob.api.endpoint.service;

import lombok.RequiredArgsConstructor;
import mz.co.checkmob.api.endpoint.domain.*;
import mz.co.checkmob.api.endpoint.persistence.EndpointRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class EndpointServiceImpl implements EndpointService {
    private final EndpointRepository endpointRepository;

    @Override
    @Transactional
    public Endpoint create(CreateEndpointCommand command) {
        return endpointRepository.save(EndpointMapper.INSTANCE.mapToModel(command));
    }

    @Override
    public Endpoint update(UpdateEndpointCommand command) {
        Endpoint endpoint = findById(command.getId());
        endpoint.setDataReader(command.getDataReader());
        endpoint.setUrl(command.getUrl());

        return endpointRepository.save(endpoint);
    }

    @Override
    public Endpoint findById(Long id) {
        return endpointRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Page<Endpoint> findAll(Pageable pageable) {
        return endpointRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Long id) {
        Endpoint endpoint = findById(id);
        endpointRepository.deleteById(endpoint.getId());
    }
}
