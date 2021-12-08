package mz.co.checkmob.api.dashboard.service;

import lombok.RequiredArgsConstructor;
import mz.co.checkmob.api.company.service.CompanyService;
import mz.co.checkmob.api.connections.service.ConnectionService;
import mz.co.checkmob.api.dashboard.presentation.DashboardJson;
import mz.co.checkmob.api.endpoint.service.EndpointService;
import mz.co.checkmob.api.user.service.UserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardService {

   private final UserService userService;
   private final ConnectionService connectionService;
   private final EndpointService endpointService;
   private final CompanyService companyService;


    public DashboardJson getResume(){
        return new DashboardJson()
                .setUsers(userService.countAllUsers())
                .setCompanies(companyService.countAllCompanies())
                .setConnections(connectionService.countAllConnections())
                .setCompanies(endpointService.countAllEndpoints());

    }
}
