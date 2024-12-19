package petermartesc.springboot.service.interfaces;

import petermartesc.springboot.exception.ResourceNotFoundException;
import petermartesc.springboot.model.Role;

import java.util.List;

public interface IRoleService {
    List<Role> getAllRoles();
    Role getRoleById(int roleId) throws ResourceNotFoundException;
    public Role createUser(Role role);
    Role updateRole(int roleId, Role roleDetails) throws ResourceNotFoundException;
    void deleteRole(int roleId) throws ResourceNotFoundException;
}
