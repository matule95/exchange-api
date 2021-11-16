package mz.co.checkmob.api.user.presentation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mz.co.checkmob.api.user.domain.CreateUserCommand;
import mz.co.checkmob.api.user.domain.UpdateUserCommand;
import mz.co.checkmob.api.user.domain.User;
import mz.co.checkmob.api.user.domain.UserMapper;
import mz.co.checkmob.api.user.domain.query.UserQuery;
import mz.co.checkmob.api.user.service.UserService;
import mz.co.checkmob.api.utils.PageJson;
import mz.co.checkmob.api.utils.ResourceResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@Api(tags = "User Management")
@RequestMapping(path = "/api/v1/users", name = "users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    @ApiOperation("Create a new user")
    public ResponseEntity<UserJson> createUser(@RequestBody @Valid CreateUserCommand command) {
        UserJson response = UserMapper.INSTANCE.mapToJson(userService.create(command));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    @ApiOperation("Fetches User Details By Id")
    public ResponseEntity<Object> getUserById(@PathVariable Long id) {
        try {
            User response = userService.findById(id);
            return ResponseEntity.ok(UserMapper.INSTANCE.mapToJson(response));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResourceResponse.error(e.getMessage(), HttpStatus.NOT_FOUND.toString()));
        }
    }

    @GetMapping
    public ResponseEntity<PageJson<UserJson>> getUsersPage(UserQuery userQuery, Pageable pageable) {
       Page<User> users = userService.fetchPaged(pageable, userQuery);
        return ResponseEntity.ok(PageJson.of(UserMapper.INSTANCE.mapToJson(users)));
    }

    @PutMapping("/{id}")
    @ApiOperation("Update User Details")
    public ResponseEntity<UserJson> update(@RequestBody @Valid UpdateUserCommand command,@PathVariable long id) {
        return  ResponseEntity.ok(UserMapper.INSTANCE.mapToJson(userService.update(command,id)));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Deletes a Single User")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteById(id);
            return ResponseEntity.status(HttpStatus.GONE).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
