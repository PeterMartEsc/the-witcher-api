package petermartesc.springboot.service;

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
import petermartesc.springboot.utilities.Utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class TestRoleService extends Utilities {

    @Mock
    RoleRepository repositoryRoleMock;

    @InjectMocks
    RoleService roleService;

    /*@InjectMocks
    RoleService roleService;*/

    @BeforeEach
    public void beforeEach (){
        MockitoAnnotations.openMocks(this);
        roleService = new RoleService();
        roleService.setRoleRepository(repositoryRoleMock);
    }
    @Test
    void getAllTest() {
        List<Role> list = new ArrayList<>();
        list.add(ROLE);
        list.add(ROLE);
        when(repositoryRoleMock.findAll()).thenReturn(list);
        Assertions.assertNotNull(roleService.getAllRoles(), NOT_EXPECTED_RESULT);
    }


    @Test
    void getOneTest() throws ResourceNotFoundException {
        when(repositoryRoleMock.findById(1)).thenReturn(Optional.of(ROLE));
        Assertions.assertNotNull(roleService.getRoleById(1), NOT_EXPECTED_RESULT);
    }



    @Test
    void updateTest() throws ResourceNotFoundException {
        //User user = new User(NAME, ROLE);
        when(repositoryRoleMock.findById(1)).thenReturn(Optional.of(ROLE));
        when(repositoryRoleMock.save(any(Role.class))).thenReturn(ROLE);
        //System.out.println(USER);
        Assertions.assertEquals(ROLE, roleService.updateRole(ID, ROLE), NOT_EXPECTED_RESULT);
    }

    @Test
    void zaddTest() throws ResourceNotFoundException {
        when(repositoryRoleMock.save(any(Role.class))).thenReturn(ROLE);
        Assertions.assertEquals(ROLE, roleService.createRole(ROLE), NOT_EXPECTED_RESULT);
    }

    @Test
    void deleteTest() throws ResourceNotFoundException {
        when(repositoryRoleMock.findById(1)).thenReturn(Optional.of(ROLE));
        doNothing().when(repositoryRoleMock).delete(isA(Role.class));
        roleService.deleteRole(1);
        verify(repositoryRoleMock, times(1)).delete(ROLE);

    }
}
