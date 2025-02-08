package petermartesc.springboot.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import petermartesc.springboot.dto.user.UserDtoInputV1;
import petermartesc.springboot.exception.ResourceNotFoundException;
import petermartesc.springboot.model.Role;
import petermartesc.springboot.model.User;
import petermartesc.springboot.service.rest.UserService;
import petermartesc.springboot.utilities.Utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class TestUserControllerV1 extends Utilities {
    @Mock
    UserService mockUserService;

    @InjectMocks
    UserControllerV1 controller;

    @BeforeEach
    public void beforeEach (){
        MockitoAnnotations.openMocks(this);
        controller = new UserControllerV1();
        controller.setUserService(mockUserService);
    }

    @Test
    void getAllTest() {
        List<User> list = new ArrayList<>();
        Role rolAdmin = new Role(1, "Admin");
        Role rolUser = new Role(2, "User");

        list.add(new User("Manuel", rolUser ));
        list.add(new User("Pedro", rolAdmin ));
        when(mockUserService.getAllUsers()).thenReturn(list);
        Assertions.assertNotNull(controller.getAllUsers(), NOT_EXPECTED_RESULT);
    }

    @Test
    void getOneTest() throws ResourceNotFoundException {
        Role rolAdmin = new Role(1, "Admin");
        Role rolUser = new Role(2, "User");
        List<User> list = new ArrayList<>();
        list.add(new User("Manuel", rolUser ));
        list.add(new User("Pedro", rolAdmin ));

        when(mockUserService.getUserById(1)).thenReturn(new User("Manuel", rolUser));
        Assertions.assertNotNull(controller.getUserById(1), NOT_EXPECTED_RESULT);
    }

    @Test
    void zaddTest() throws ResourceNotFoundException {
        Role rolAdmin = new Role(1, "Admin");
        User user = new User(1,"Ejemplo", PASSWORD, rolAdmin );
        UserDtoInputV1 dto = new UserDtoInputV1( user.getName(), user.getPassword(), user.getRole().getId() );
        when(mockUserService.createUser(any(User.class))).thenReturn(user);
        User userResponse = controller.createUser(dto);
        Assertions.assertEquals(user, userResponse, NOT_EXPECTED_RESULT);
    }

    @Test
    void deleteTest() throws ResourceNotFoundException {
        Map<String, Boolean> expectedResponse = new HashMap<>();
        expectedResponse.put("deleted", Boolean.TRUE);
        Map<String, Boolean> responseEntity = controller.deleteUser(1);
        Assertions.assertEquals(expectedResponse, responseEntity, NOT_EXPECTED_RESULT);
    }

    @Test
    void updateTest() throws ResourceNotFoundException {
        Role rolUser = new Role(2, "User");
        User user = new User(1,"Ejemplo", PASSWORD, rolUser );
        UserDtoInputV1 dto = new UserDtoInputV1( user.getName(), user.getPassword(), user.getRole().getId() );

        when(mockUserService.updateUser(2, user)).thenReturn(user);
        ResponseEntity responseEntity = controller.updateUser(2, dto);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode(), NOT_EXPECTED_RESULT);
    }

}
