package mz.co.checkmob.api.authorization.persistence;

import mz.co.checkmob.api.authorization.domain.Authorization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorizationRepository extends JpaRepository<Authorization, Long> {
}