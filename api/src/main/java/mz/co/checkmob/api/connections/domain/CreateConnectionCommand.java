package mz.co.checkmob.api.connections.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateConnectionCommand {

    @NotNull
    @NotEmpty
    private Long fromThirdParty;
    @NotNull
    @NotEmpty
    private String fromUrl;
    @NotNull
    @NotEmpty
    private RequestType fromRequestType;

    @NotNull
    @NotEmpty
    private Long toThirdParty;
    @NotNull
    @NotEmpty
    private String toUrl;
    @NotNull
    @NotEmpty
    private RequestType toRequestType;

    List<Param> params;

    OperationType operationType;



}
