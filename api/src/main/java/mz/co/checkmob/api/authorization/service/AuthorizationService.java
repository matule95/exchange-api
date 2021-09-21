package mz.co.checkmob.api.authorization.service;

import mz.co.checkmob.api.authorization.domain.Authorization;
import mz.co.checkmob.api.authorization.domain.AuthorizationCommand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuthorizationService {
    Authorization create(AuthorizationCommand command);
    Authorization findById(Long id);
    Page<Authorization> findAll(Pageable pageable);
    void deleteById(Long id);
    Authorization update(Long id, AuthorizationCommand command);
}