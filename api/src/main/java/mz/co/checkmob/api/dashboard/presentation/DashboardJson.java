package mz.co.checkmob.api.dashboard.presentation;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DashboardJson {
    private long users;
    private long companies;
    private long connections;
    private long thirdParties;
}
