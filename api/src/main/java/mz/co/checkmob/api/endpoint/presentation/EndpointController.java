package mz.co.checkmob.api.endpoint.presentation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mz.co.checkmob.api.endpoint.domain.CreateEndpointCommand;
import mz.co.checkmob.api.endpoint.domain.Endpoint;
import mz.co.checkmob.api.endpoint.domain.UpdateEndpointCommand;
import mz.co.checkmob.api.endpoint.domain.EndpointMapper;
import mz.co.checkmob.api.endpoint.service.EndpointService;
import mz.co.checkmob.api.utils.PageJson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@CrossOrigin
@RestController
@Api(tags = "Endpoints Management")
@RequestMapping(path = "/api/v1/endpoints", name = "endpoints")
@RequiredArgsConstructor
public class EndpointController {
    private final EndpointService endpointService;

    @PostMapping
    @ApiOperation("Create a new Endpoint")
    public ResponseEntity<EndpointJson> create(@RequestBody @Valid CreateEndpointCommand command) {
        return ResponseEntity.status(HttpStatus.CREATED).body(EndpointMapper.INSTANCE.mapToJson(endpointService.create(command)));
    }

    @PutMapping
    @ApiOperation("Update Endpoint Details")
    public ResponseEntity<EndpointJson> update(@RequestBody @Valid UpdateEndpointCommand command) {
        return  ResponseEntity.ok(EndpointMapper.INSTANCE.mapToJson(endpointService.update(command)));
    }

    @GetMapping("/company/{id}")
    @ApiOperation("Fetch Endpoint by Company")
    public ResponseEntity<PageJson<EndpointJson>> getCompanyEndpoint(@PathVariable Long id, Pageable pageable) {
        return  ResponseEntity.ok(PageJson.of(endpointService.findAllByCompanyId(id,pageable)));
    }

    @GetMapping("/{id}")
    @ApiOperation("Fetches Endpoint Details By Id")
    public ResponseEntity<Object> getEndpoint(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(EndpointMapper.INSTANCE.mapToJson(endpointService.findById(id)));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping
    @ApiOperation("Fetch all Endpoints")
    public ResponseEntity<PageJson<EndpointJson>> getAll( Pageable pageable) {
        return ResponseEntity.ok(PageJson.of(endpointService.findAll(pageable)));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Deletes a Single Endpoint")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            endpointService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
