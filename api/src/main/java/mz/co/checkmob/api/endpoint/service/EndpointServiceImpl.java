package mz.co.checkmob.api.endpoint.service;

import lombok.RequiredArgsConstructor;
import mz.co.checkmob.api.company.persistence.CompanyRepository;
import mz.co.checkmob.api.company.service.CompanyService;
import mz.co.checkmob.api.endpoint.domain.*;
import mz.co.checkmob.api.endpoint.domain.query.EndpointQuery;
import mz.co.checkmob.api.endpoint.domain.query.EndpointSpecification;
import mz.co.checkmob.api.endpoint.persistence.EndpointRepository;
import mz.co.checkmob.api.endpoint.presentation.EndpointJson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class EndpointServiceImpl implements EndpointService {
    private final EndpointRepository endpointRepository;
    private final CompanyRepository companyRepository;
    private final EndpointSpecification endpointSpecification;

    @Override
    @Transactional
    public Endpoint create(CreateEndpointCommand command) {
        Endpoint endpoint = EndpointMapper.INSTANCE.mapToModel(command);
        endpoint.setCompany(companyRepository.findById(command.getCompanyId()).orElseThrow(EntityNotFoundException::new));
        return endpointRepository.save(endpoint);
    }

    @Override
    public Endpoint update(UpdateEndpointCommand command) {
        Endpoint endpoint = findById(command.getId());
        endpoint.setDataReader(command.getDataReader());
        endpoint.setUrl(command.getUrl());

        return endpointRepository.save(endpoint);
    }

    @Override
    public Page<EndpointJson> findAllByCompanyId(Long id, Pageable pageable) {
        return endpointRepository.findAllByCompanyId(id,pageable);

    }

    @Override
    public Endpoint findById(Long id) {
        return endpointRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Page<EndpointJson> findAll(Pageable pageable, EndpointQuery endpointQuery) {
        return EndpointMapper.INSTANCE.mapToJson(
                endpointSpecification.executeQuery(endpointQuery,pageable));
    }

    @Override
    public void deleteById(Long id) {
        Endpoint endpoint = findById(id);
        endpointRepository.deleteById(endpoint.getId());
    }

    @Override
    public long countAllEndpoints() {
        return endpointRepository.count();
    }
}
