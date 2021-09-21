package mz.co.checkmob.api.authorization.presentation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mz.co.checkmob.api.authorization.domain.AuthorizationCommand;
import mz.co.checkmob.api.authorization.domain.Authorization;
import mz.co.checkmob.api.authorization.domain.AuthorizationMapper;
import mz.co.checkmob.api.authorization.service.AuthorizationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@CrossOrigin
@RestController
@Api(tags = "Authorization Management")
@RequestMapping(path = "/api/v1/authorizations", name = "authorizations")
@RequiredArgsConstructor
public class AuthorizationController {
    private final AuthorizationService authorizationService;

    @PostMapping
    @ApiOperation("Create a new Authorization")
    public ResponseEntity<AuthorizationJson> save(@RequestBody @Valid AuthorizationCommand command) {
        AuthorizationJson response = AuthorizationMapper.INSTANCE.mapToJson(authorizationService.create(command));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    @ApiOperation("Fetches Authorization Details By Id")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        try {
            Authorization response = authorizationService.findById(id);
            return ResponseEntity.ok(AuthorizationMapper.INSTANCE.mapToJson(response));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping
    @ApiOperation("Fetches All Authorizations")
    public ResponseEntity<Page<AuthorizationJson>> findAll(Pageable pageable) {
        Page<Authorization> authorizations = authorizationService.findAll(pageable);
        return ResponseEntity.ok(AuthorizationMapper.INSTANCE.mapToJson(authorizations));
    }

    @PutMapping("/{id}")
    @ApiOperation("Update Authorization Details")
    public ResponseEntity<AuthorizationJson> update(@PathVariable Long id, @RequestBody @Valid AuthorizationCommand command) {
        return  ResponseEntity.ok(AuthorizationMapper.INSTANCE.mapToJson(authorizationService.update(id, command)));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Deletes a Single Authorization")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            authorizationService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}