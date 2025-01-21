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
import petermartesc.springboot.model.User;
import petermartesc.springboot.service.rest.UserService;
import petermartesc.springboot.utilities.Utilities;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class TestUserController extends Utilities {
    @Mock
    UserService mockUserService;

    @InjectMocks
    UserController controller;

    @BeforeEach
    public void beforeEach (){
        MockitoAnnotations.openMocks(this);
        controller = new UserController();
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
        when(mockUserService.getAllUsers()).thenReturn(list);
        Assertions.assertNotNull(controller.getUserById(1), NOT_EXPECTED_RESULT);
    }

    @Test
    void addTest() {
        when(mockUserService.createUser(any(User.class))).thenReturn(true);;
        Role rolAdmin = new Role(1, "Admin");
        User user = new User("Ejemplo", rolAdmin );
        ResponseEntity responseEntity = controller.createUser(user);
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode(), NOT_EXPECTED_RESULT);
    }
}
