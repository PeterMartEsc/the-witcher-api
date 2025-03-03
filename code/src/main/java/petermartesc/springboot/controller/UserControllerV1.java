package petermartesc.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import petermartesc.springboot.dto.user.UserDtoInputV1;
import petermartesc.springboot.dto.user.UserDtoOutV1;
import petermartesc.springboot.model.Role;
import petermartesc.springboot.model.User;
import petermartesc.springboot.service.rest.interfaces.IUserService;
import jakarta.validation.Valid;

import petermartesc.springboot.exception.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserControllerV1 {

    private IUserService userService;

    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Get all users")
    @GetMapping("/")
    public List<UserDtoOutV1> getAllUsers() {
        List<UserDtoOutV1> dtoUsers = userService.getAllUsers()
                .stream()
                .map(user -> new UserDtoOutV1(
                                user.getId(),
                                user.getName(),
                                user.getPassword(),
                                user.getRole().getId()
                        )
                ).collect(Collectors.toList());
        return dtoUsers;
    }

    @Operation(summary = "Get user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserDtoOutV1> getUserById(@PathVariable(value = "id") int userId) throws ResourceNotFoundException {
        User user = userService.getUserById(userId);
        UserDtoOutV1 dto = new UserDtoOutV1(
                user.getId(),
                user.getName(),
                user.getPassword(),
                user.getRole().getId()
        );
        return ResponseEntity.ok().body(dto);
    }

    @Operation(summary = "Insert user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/add")
    public User createUser(@Valid @RequestBody UserDtoInputV1 dto) throws ResourceNotFoundException {
        User user = new User();
        user.setName(dto.name());
        user.setPassword(dto.password());

        Role role = new Role();
        role.setId(dto.role());
        user.setRole(role);

        return userService.createUser(user);
    }

    @Operation(summary = "Update user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") int userId,
                                           @Valid @RequestBody UserDtoInputV1 dto) throws ResourceNotFoundException {
        User user = new User();
        user.setName(dto.name());
        user.setPassword(dto.password());

        Role role = new Role();
        role.setId(dto.role());
        user.setRole(role);
        /*final*/ User updatedUser = userService.updateUser(userId, user);

        return ResponseEntity.ok(updatedUser);
    }

    @Operation(summary = "Delete user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @DeleteMapping("/delete/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") int userId) throws ResourceNotFoundException {
        userService.deleteUser(userId); 
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
