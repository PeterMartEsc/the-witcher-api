package petermartesc.springboot.service.rest;

import petermartesc.springboot.exception.ResourceNotFoundException;
import petermartesc.springboot.model.Role;
import petermartesc.springboot.repository.RoleRepository;
import petermartesc.springboot.service.rest.interfaces.IRoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component
public class RoleService implements IRoleService {

    private RoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository= roleRepository;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleById(@PathVariable(value = "id") int roleId) throws ResourceNotFoundException {
        return roleRepository.findById(roleId).orElseThrow(() -> new ResourceNotFoundException("Role not found for this id :: " + roleId));
    }

    @Override
    public Role createRole(@Valid @RequestBody Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role updateRole(@PathVariable(value = "id") int roleId, @Valid @RequestBody Role roleDetails) throws ResourceNotFoundException {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found for this id :: " + roleId));

        role.setRoleName(roleDetails.getRoleName());
        return roleRepository.save(role);
    }

    @Override
    public void deleteRole(@PathVariable(value = "id") int roleId) throws ResourceNotFoundException {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found for this id :: " + roleId));

        roleRepository.delete(role);
    }
}
