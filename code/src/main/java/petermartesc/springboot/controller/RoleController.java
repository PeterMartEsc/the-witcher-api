package petermartesc.springboot.controller;

import petermartesc.springboot.exception.ResourceNotFoundException;

import petermartesc.springboot.model.Role;
import petermartesc.springboot.service.rest.interfaces.IRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {
    private IRoleService roleService;

    @Autowired
    public void setRoleRepository(IRoleService roleService) {
        this.roleService = roleService;
    }

    @Operation(summary = "Get all roles")
    @GetMapping("/")
    public List<Role> getAllUsers() {
        return roleService.getAllRoles();
    }

    @Operation(summary = "Get role by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Role not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable(value = "id") int roleId) throws ResourceNotFoundException {
        Role role = roleService.getRoleById(roleId);
        return ResponseEntity.ok().body(role);
    }

    @Operation(summary = "Insert role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/add")
    public Role createRole(@Valid @RequestBody Role role) {
        return roleService.createRole(role);
    }

    @Operation(summary = "Update role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role updated successfully"),
            @ApiResponse(responseCode = "404", description = "Role not found")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable(value = "id") int roleId, @Valid @RequestBody Role roleDetails)
            throws ResourceNotFoundException {
        final Role updateRole = roleService.updateRole(roleId, roleDetails);
        return ResponseEntity.ok(updateRole);
    }

    @Operation(summary = "Delete role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Role not found")
    })
    @DeleteMapping("/delete/{id}")
    public Map<String, Boolean> deleteRole(@PathVariable(value = "id") int roleId) throws ResourceNotFoundException {
        roleService.deleteRole(roleId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
