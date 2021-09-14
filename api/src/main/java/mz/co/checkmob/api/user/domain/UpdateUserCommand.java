package mz.co.checkmob.api.user.domain;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UpdateUserCommand {
    private Long id;
    @NotNull
    @NotEmpty
    @Size(min = 2)
    private String name;

    @NotNull private UserRole role;
    @NotNull private Long delegationId;
    @NotNull private Long departmentId;

}
