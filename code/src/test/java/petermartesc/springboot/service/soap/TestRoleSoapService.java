package petermartesc.springboot.service.soap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import petermartesc.springboot.exception.ResourceNotFoundException;
import petermartesc.springboot.model.Alchemy;
import petermartesc.springboot.model.Role;
import petermartesc.springboot.service.rest.RoleService;
import petermartesc.springboot.utilities.Utilities;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TestRoleSoapService extends Utilities {

    @Mock
    RoleService repositoryRoleMock;

    @InjectMocks
    RoleServiceSoap roleSoapService;

    @BeforeEach
    public void beforeEach (){
        MockitoAnnotations.openMocks(this);
        roleSoapService = new RoleServiceSoap();
        roleSoapService.setRoleRepository(repositoryRoleMock);
    }


    @Test
    void getAllTest() {
        List<Role> list = new ArrayList<>();
        list.add(ROLE);
        list.add(ROLE);
        when(repositoryRoleMock.getAllRoles()).thenReturn(list);
        Assertions.assertNotNull(roleSoapService.getAllRoles(), NOT_EXPECTED_RESULT);
    }


    @Test
    void getOneTest() throws ResourceNotFoundException {
        when(repositoryRoleMock.getRoleById(1)).thenReturn(ROLE);
        Assertions.assertNotNull(roleSoapService.getRoleById(1), NOT_EXPECTED_RESULT);
    }


    @Test
    void updateTest() throws ResourceNotFoundException {
        //User user = new User(NAME, ROLE);
        when(repositoryRoleMock.getRoleById(1)).thenReturn(ROLE);
        when(repositoryRoleMock.updateRole(ID, ROLE)).thenReturn(ROLE);
        //System.out.println(USER);
        Assertions.assertTrue(roleSoapService.updateRole(ROLE, ID), NOT_EXPECTED_RESULT);
    }


    @Test
    void zaddTest() throws ResourceNotFoundException {
        when(repositoryRoleMock.createRole(any(Role.class))).thenReturn(ROLE);
        Assertions.assertTrue(roleSoapService.createRole(ROLE), NOT_EXPECTED_RESULT);
    }


    @Test
    void deleteTest() throws ResourceNotFoundException {
        Role role = new Role(1, NAME);
        when(repositoryRoleMock.getRoleById(1)).thenReturn(role);
        doNothing().when(repositoryRoleMock).deleteRole(isA(Integer.class));
        roleSoapService.deleteRoleById(1);
        verify(repositoryRoleMock, times(1)).deleteRole(1);
    }
}
