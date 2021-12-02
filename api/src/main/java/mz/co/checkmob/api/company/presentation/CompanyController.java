package mz.co.checkmob.api.company.presentation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mz.co.checkmob.api.company.domain.CompanyStatus;
import mz.co.checkmob.api.company.domain.CreateCompanyCommand;
import mz.co.checkmob.api.company.domain.UpdateCompanyCommand;
import mz.co.checkmob.api.company.domain.query.CompanyQuery;
import mz.co.checkmob.api.company.service.CompanyServiceImpl;
import mz.co.checkmob.api.utils.PageJson;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@Api(tags = "Company Management")
@RequestMapping(path = "/api/v1/companies", name = "companies")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyServiceImpl companyService;


    @PostMapping
    @ApiOperation("Create a new company")
    public ResponseEntity<CompanyJson> createCompany(@RequestBody @Valid CreateCompanyCommand command) {
        return ResponseEntity.status(HttpStatus.CREATED).body(companyService.create(command));
    }

    @GetMapping("/{id}")
    @ApiOperation("Fetch Company By Id")
    public ResponseEntity<CompanyJson> geCompanyById(@PathVariable Long id) {
            return ResponseEntity.ok(companyService.fetchCompany(id));
    }

    @GetMapping
    @ApiOperation("Fetch All Companies")
    public ResponseEntity<PageJson<CompanyJson>> getCompanies(CompanyQuery companyQuery, Pageable pageable) {
        return ResponseEntity.ok(PageJson.of(companyService.fetchCompanies(pageable,companyQuery)));
    }

    @PutMapping("/{id}")
    @ApiOperation("Update Company Details")
    public ResponseEntity<CompanyJson> updateCompany(@PathVariable Long id,@RequestBody @Valid UpdateCompanyCommand command) {
        return  ResponseEntity.ok(companyService.update(command,id));
    }

    @PutMapping("status/{id}/{status}")
    @ApiOperation("Update Company Status")
    public ResponseEntity<CompanyJson> updateStatus(@PathVariable Long id, CompanyStatus status){
        return ResponseEntity.ok(companyService.setStatus(id,status));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete Company")
    public ResponseEntity<?> deleteCompany(@PathVariable Long id) {
            companyService.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
    }
}
