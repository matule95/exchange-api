package mz.co.checkmob.api.dashboard.presentation;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import mz.co.checkmob.api.dashboard.service.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dashboard")
@RequiredArgsConstructor
@Api(tags = "Dashboard Management")
public class DashboardController {
    private final DashboardService dashboardService;

    @GetMapping
    public ResponseEntity<DashboardJson> getDashboardResume(){
        return ResponseEntity.ok(dashboardService.getResume());
    }
}
