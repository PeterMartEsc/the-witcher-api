package petermartesc.springboot.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import petermartesc.springboot.exception.ResourceNotFoundException;
import petermartesc.springboot.model.Role;
import petermartesc.springboot.model.User;
import petermartesc.springboot.repository.RoleRepository;
import petermartesc.springboot.repository.UserRepository;
import petermartesc.springboot.service.rest.RoleService;
import petermartesc.springboot.service.rest.UserService;
import petermartesc.springboot.utilities.Utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

public class TestUserService extends Utilities {
    @Mock
    UserRepository repositoryUserMock;
    @Mock
    RoleRepository repositoryRoleMock;

    @InjectMocks
    UserService userService;

    /*@InjectMocks
    RoleService roleService;*/

    @BeforeEach
    public void beforeEach (){
        MockitoAnnotations.openMocks(this);
        userService = new UserService();
        //roleService = new RoleService();
        userService.setUserRepository(repositoryUserMock);
        userService.setRoleRepository(repositoryRoleMock);
    }
    @Test
    void getAllTest() {
        List<User> list = new ArrayList<>();
        list.add(new User(NAME, ROLE));
        list.add(new User(NAME, ROLE));
        when(repositoryUserMock.findAll()).thenReturn(list);
        Assertions.assertNotNull(userService.getAllUsers(), NOT_EXPECTED_RESULT);
    }


    @Test
    void getOneTest() throws ResourceNotFoundException {
        when(repositoryUserMock.findById(1)).thenReturn(Optional.of(new User()));
        Assertions.assertNotNull(userService.getUserById(1), NOT_EXPECTED_RESULT);
    }

    @Test
    void addTest() throws ResourceNotFoundException {
        //when(repositoryMock.existsById(1)).thenReturn(false);
        //User user = new User(NAME, ROLE);
        when(repositoryUserMock.save(any(User.class))).thenReturn(USER);
        when(repositoryRoleMock.findById(1)).thenReturn(Optional.of(new Role()));
        Assertions.assertEquals(USER, userService.createUser(USER), NOT_EXPECTED_RESULT);
    }

    @Test
    void updateTest() throws ResourceNotFoundException {
        //User user = new User(NAME, ROLE);
        when(repositoryUserMock.findById(1)).thenReturn(Optional.of(USER));
        when(repositoryRoleMock.findById(1)).thenReturn(Optional.of(ROLE));
        when(repositoryUserMock.save(any(User.class))).thenReturn(USER);
        Assertions.assertEquals(USER, userService.updateUser(ID, USER), NOT_EXPECTED_RESULT);
    }

    @Test
    void deleteTest() throws ResourceNotFoundException {
        when(repositoryUserMock.findById(1)).thenReturn(Optional.of(USER));
        doNothing().when(repositoryUserMock).delete(isA(User.class));
        userService.deleteUser(1);
        verify(repositoryUserMock, times(1)).delete(USER);

    }

    @AfterEach
    public void afterEach(){

    }
}
