package petermartesc.springboot.service.soap;

import jakarta.xml.ws.WebServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import petermartesc.springboot.exception.ResourceNotFoundException;
import petermartesc.springboot.model.Role;
import petermartesc.springboot.service.rest.interfaces.IRoleService;
import petermartesc.springboot.service.soap.interfaces.IRoleServiceSoap;

import java.util.List;

public class RoleServiceSoap implements IRoleServiceSoap {

    private IRoleServiceSoap roleService;

    @Autowired
    public void setRoleRepository(IRoleServiceSoap roleService) {
        this.roleService = roleService;
    }
    
    @Override
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @Override
    public Role getRoleById(int roleId) {
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
    public boolean updateRole(Role role, int roleId) {
        roleService.updateRole( role, roleId);

        return true;
    }

    @Override
    public boolean deleteRoleById(int roleId) {
        roleService.deleteRoleById(roleId);
        return true;
    }
}
