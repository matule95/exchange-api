package mz.co.checkmob.api.authorization.service;

import lombok.RequiredArgsConstructor;
import mz.co.checkmob.api.authorization.domain.*;
import mz.co.checkmob.api.authorization.persistence.AuthorizationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class AuthorizationServiceImpl implements AuthorizationService {
    private final AuthorizationRepository authorizationRepository;

    @Override
    @Transactional
    public Authorization create(AuthorizationCommand command) {
        return authorizationRepository.save(AuthorizationMapper.INSTANCE.mapToModel(command));
    }

    @Override
    public Authorization findById(Long id) {
        return authorizationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("A autenticação pretendida não foi encontrada."));
    }

    @Override
    public Page<Authorization> findAll(Pageable pageable) {
        return authorizationRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Long id) {
        Authorization authorization = findById(id);
        authorizationRepository.deleteById(authorization.getId());
    }

    @Override
    public Authorization update(Long id, AuthorizationCommand command) {
        Authorization authorization = findById(id);
        authorization.setAuthJson(command.getAuthJson());
        authorization.setType(command.getType());
        return authorizationRepository.save(authorization);
    }
}