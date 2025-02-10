package petermartesc.springboot.service.soap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import petermartesc.springboot.exception.ResourceNotFoundException;
import petermartesc.springboot.model.Role;
import petermartesc.springboot.repository.RoleRepository;
import petermartesc.springboot.service.rest.RoleService;
import petermartesc.springboot.service.soap.interfaces.IRoleServiceSoap;
import petermartesc.springboot.utilities.Utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class TestRoleSoapService extends Utilities {

    @Mock
    IRoleServiceSoap repositoryRoleMock;

    @InjectMocks
    RoleServiceSoap roleService;

    /*@InjectMocks
    RoleService roleService;*/

    @BeforeEach
    public void beforeEach (){
        MockitoAnnotations.openMocks(this);
        roleService = new RoleServiceSoap();
        roleService.setRoleRepository(repositoryRoleMock);
    }
    @Test
    void getAllTest() {
        List<Role> list = new ArrayList<>();
        list.add(ROLE);
        list.add(ROLE);
        when(repositoryRoleMock.getAllRoles()).thenReturn(list);
        Assertions.assertNotNull(roleService.getAllRoles(), NOT_EXPECTED_RESULT);
    }


    @Test
    void getOneTest() throws ResourceNotFoundException {
        when(repositoryRoleMock.getRoleById(1)).thenReturn(ROLE);
        Assertions.assertNotNull(roleService.getRoleById(1), NOT_EXPECTED_RESULT);
    }



    @Test
    void updateTest() throws ResourceNotFoundException {
        //User user = new User(NAME, ROLE);
        when(repositoryRoleMock.getRoleById(1)).thenReturn(ROLE);
        when(repositoryRoleMock.updateRole(ROLE, ID)).thenReturn(true);
        //System.out.println(USER);
        Assertions.assertTrue(roleService.updateRole(ROLE, ID), NOT_EXPECTED_RESULT);
    }

    @Test
    void zaddTest() throws ResourceNotFoundException {
        when(repositoryRoleMock.createRole(any(Role.class))).thenReturn(true);
        Assertions.assertTrue(roleService.createRole(ROLE), NOT_EXPECTED_RESULT);
    }

    @Test
    void deleteTest() throws ResourceNotFoundException {
        when(repositoryRoleMock.deleteRoleById(1)).thenReturn(true);
        Assertions.assertTrue(roleService.deleteRoleById(ID), NOT_EXPECTED_RESULT);
    }
}
