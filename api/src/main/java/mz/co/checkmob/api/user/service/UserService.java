package mz.co.checkmob.api.user.service;

import mz.co.checkmob.api.company.domain.CompanyStatus;
import mz.co.checkmob.api.company.presentation.CompanyJson;
import mz.co.checkmob.api.user.domain.CreateUserCommand;
import mz.co.checkmob.api.user.domain.UpdateUserCommand;
import mz.co.checkmob.api.user.domain.User;
import mz.co.checkmob.api.user.domain.UserStatus;
import mz.co.checkmob.api.user.domain.query.UserQuery;
import mz.co.checkmob.api.user.presentation.UserJson;
import mz.co.checkmob.api.utils.PageJson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    User create(CreateUserCommand command);
    User findById(Long id);
    Page<User> fetchPaged(Pageable pageable, UserQuery userQuery);
    void deleteById(Long id);
    User update(UpdateUserCommand command, Long id);

    UserJson setStatus(Long userId, UserStatus status);

    long countAllUsers();
}
