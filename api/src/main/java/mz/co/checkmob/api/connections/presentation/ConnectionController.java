package mz.co.checkmob.api.connections.presentation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mz.co.checkmob.api.connections.domain.Connection;
import mz.co.checkmob.api.connections.domain.ConnectionMapper;
import mz.co.checkmob.api.connections.domain.CreateConnectionCommand;
import mz.co.checkmob.api.connections.domain.query.ConnectionQuery;
import mz.co.checkmob.api.connections.domain.query.ConnectionSpecification;
import mz.co.checkmob.api.connections.service.ConnectionService;
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
@Api(tags = "Connections Management")
@RequestMapping(path = "/api/v1/connections", name = "connections")
@RequiredArgsConstructor
public class ConnectionController {
    private final ConnectionService connectionService;
    private final ConnectionSpecification connectionSpecification;

    @PostMapping
    @ApiOperation("Create a new Connection")
    public ResponseEntity<ConnectionJson> create(@RequestBody @Valid CreateConnectionCommand command) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ConnectionMapper.INSTANCE.mapToJson(connectionService.create(command)));
    }


    @GetMapping("/{id}")
    @ApiOperation("Fetches Connection Details By Id")
    public ResponseEntity<Object> getEndpoint(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(ConnectionMapper.INSTANCE.mapToJson(connectionService.findById(id)));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @GetMapping
    @ApiOperation("Fetch all Connections")
    public ResponseEntity<PageJson<ConnectionJson>> getAll(Pageable pageable,ConnectionQuery connectionQuery) {
        return ResponseEntity.ok(PageJson.of(connectionService.findAll(pageable, connectionQuery)));


    }

    @DeleteMapping("/{id}")
    @ApiOperation("Deletes a Single Connection")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            connectionService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
