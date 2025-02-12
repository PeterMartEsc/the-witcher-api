package petermartesc.springboot.service.soap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import petermartesc.springboot.exception.ResourceNotFoundException;
import petermartesc.springboot.model.User;
import petermartesc.springboot.service.rest.UserService;
import petermartesc.springboot.utilities.Utilities;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class TestUserSoapService extends Utilities {

    @Mock
    UserService repositoryUserMock;

    @InjectMocks
    UserServiceSoap userSoapService;

    @BeforeEach
    public void beforeEach (){
        MockitoAnnotations.openMocks(this);
        userSoapService = new UserServiceSoap();
        userSoapService.setUserRepository(repositoryUserMock);
    }


    @Test
    void getAllTest() {
        List<User> list = new ArrayList<>();
        list.add(USER);
        list.add(USER);
        when(repositoryUserMock.getAllUsers()).thenReturn(list);
        Assertions.assertNotNull(userSoapService.getAllUsers(), NOT_EXPECTED_RESULT);
    }


    @Test
    void getOneTest() throws ResourceNotFoundException {
        when(repositoryUserMock.getUserById(1)).thenReturn(USER);
        Assertions.assertNotNull(userSoapService.getUserById(1), NOT_EXPECTED_RESULT);
    }


    @Test
    void updateTest() throws ResourceNotFoundException {
        //User user = new User(NAME, USER);
        when(repositoryUserMock.getUserById(1)).thenReturn(USER);
        when(repositoryUserMock.updateUser(ID, USER)).thenReturn(USER);
        //System.out.println(USER);
        Assertions.assertTrue(userSoapService.updateUser(USER, ID), NOT_EXPECTED_RESULT);
    }


    @Test
    void zaddTest() throws ResourceNotFoundException {
        when(repositoryUserMock.createUser(any(User.class))).thenReturn(USER);
        Assertions.assertTrue(userSoapService.createUser(USER), NOT_EXPECTED_RESULT);
    }


    @Test
    void deleteTest() throws ResourceNotFoundException {
        User user = new User(1, NAME, PASSWORD, ROLE);
        when(repositoryUserMock.getUserById(1)).thenReturn(user);
        doNothing().when(repositoryUserMock).deleteUser(isA(Integer.class));
        userSoapService.deleteUserById(1);
        verify(repositoryUserMock, times(1)).deleteUser(1);
    }
}
