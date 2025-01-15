package petermartesc.springboot.service.soap.interfaces;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import petermartesc.springboot.model.Role;

import java.util.List;

@WebService(targetNamespace = "springboot.service.soap")
public interface IRoleServiceSoap {
    @WebMethod
    @WebResult(
            name="role")
    List<Role> getAllRoles();

    @WebMethod
    Role getRoleById(@WebParam(name = "roleId") int roleId);

    @WebMethod
    boolean createRole(@WebParam(name = "role") Role role);
    @WebMethod
    boolean updateRole(@WebParam(name = "role") Role role, @WebParam(name = "roleId") int roleId);

    @WebMethod
    boolean deleteRoleById(@WebParam(name = "roleId") int roleId);
}
