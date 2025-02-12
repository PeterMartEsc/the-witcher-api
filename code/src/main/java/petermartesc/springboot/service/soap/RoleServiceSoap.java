package petermartesc.springboot.service.soap;

import jakarta.xml.ws.WebServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import petermartesc.springboot.exception.ResourceNotFoundException;
import petermartesc.springboot.model.Role;
import petermartesc.springboot.service.rest.interfaces.IRoleService;
import petermartesc.springboot.service.soap.interfaces.IRoleServiceSoap;

import java.util.List;

public class RoleServiceSoap implements IRoleServiceSoap {

    private IRoleService roleService;

    @Autowired
    public void setRoleRepository(IRoleService roleService) {
        this.roleService = roleService;
    }
    
    @Override
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @Override
    public Role getRoleById(int roleId) throws ResourceNotFoundException {
        return roleService.getRoleById(roleId);
    }

    @Override
    public boolean createRole(Role role) {
        if(role == null){
            return false;
        }
        roleService.createRole(role);
        return true;
    }

    @Override
    public boolean updateRole(Role role, int roleId) throws ResourceNotFoundException {
        roleService.updateRole(roleId, role);
        return true;
    }

    @Override
    public boolean deleteRoleById(int roleId) throws ResourceNotFoundException {
        roleService.deleteRole(roleId);
        return true;
    }
}
