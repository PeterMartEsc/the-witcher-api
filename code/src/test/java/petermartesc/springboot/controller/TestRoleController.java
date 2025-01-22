package petermartesc.springboot.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import petermartesc.springboot.exception.ResourceNotFoundException;
import petermartesc.springboot.model.Role;
import petermartesc.springboot.service.rest.RoleService;
import petermartesc.springboot.utilities.Utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class TestRoleController extends Utilities {

    @Mock
    RoleService mockRoleService;

    @InjectMocks
    RoleController controller;

    @BeforeEach
    public void beforeEach (){
        MockitoAnnotations.openMocks(this);
        controller = new RoleController();
        controller.setRoleService(mockRoleService);
    }

    @Test
    void getAllTest() {
        List<Role> list = new ArrayList<>();
        list.add(new Role(1, "Admin"));
        list.add(new Role(2, "Role"));

        when(mockRoleService.getAllRoles()).thenReturn(list);
        Assertions.assertNotNull(controller.getAllRoles(), NOT_EXPECTED_RESULT);
    }

    @Test
    void getOneTest() throws ResourceNotFoundException {

        when(mockRoleService.getRoleById(1)).thenReturn(new Role(1, "Admin"));
        Assertions.assertNotNull(controller.getRoleById(1), NOT_EXPECTED_RESULT);
    }

    @Test
    void addTest() {
        Role rol = new Role(3, "Ejemplo");

        when(mockRoleService.createRole(any(Role.class))).thenReturn(rol);
        Role roleResponse = controller.createRole(rol);
        Assertions.assertEquals(rol, roleResponse, NOT_EXPECTED_RESULT);
    }

    @Test
    void deleteTest() throws ResourceNotFoundException {
        Map<String, Boolean> expectedResponse = new HashMap<>();
        expectedResponse.put("deleted", Boolean.TRUE);

        Map<String, Boolean> responseEntity = controller.deleteRole(1);
        Assertions.assertEquals(expectedResponse, responseEntity, NOT_EXPECTED_RESULT);
    }

    @Test
    void updateTest() throws ResourceNotFoundException {
        Role rolRole = new Role(2, "Role");

        when(mockRoleService.updateRole(2, rolRole)).thenReturn(rolRole);
        ResponseEntity responseEntity = controller.updateRole(2, rolRole);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode(), NOT_EXPECTED_RESULT);
    }
}
