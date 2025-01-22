package petermartesc.springboot.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import petermartesc.springboot.exception.ResourceNotFoundException;
import petermartesc.springboot.model.User;
import petermartesc.springboot.repository.UserRepository;
import petermartesc.springboot.service.rest.UserService;
import petermartesc.springboot.service.rest.interfaces.IUserService;
import petermartesc.springboot.utilities.Utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class TestUserService extends Utilities {
    @Mock
    UserRepository repositoryMock;

    @InjectMocks
    UserService service;

    @BeforeEach
    public void beforeEach (){
        MockitoAnnotations.openMocks(this);
        service = new UserService();
        service.setUserRepository(repositoryMock);
    }
    @Test
    void getAllTest() {
        List<User> list = new ArrayList<>();
        list.add(new User(NAME, ROLE));
        list.add(new User(NAME, ROLE));
        when(repositoryMock.findAll()).thenReturn(list);
        Assertions.assertNotNull(service.getAllUsers(), NOT_EXPECTED_RESULT);
    }


    @Test
    void getOneTest() throws ResourceNotFoundException {
        when(repositoryMock.findById(1)).thenReturn(Optional.of(new User()));
        Assertions.assertNotNull(service.getUserById(1), NOT_EXPECTED_RESULT);
    }

    /*@Test
    void addTest() {
        when(repositoryMock.existsById(1)).thenReturn(false);
        User user = new User(NAME, ROLE);
        when(repositoryMock.save(any(User.class))).thenReturn(user);
        Assertions.assertEquals(user, service.createUser(user), NOT_EXPECTED_RESULT);
    }

    @Test
    void updateTest() throws Exception {
        when(repositoryMock.findById(1)).thenReturn(Optional.of(USER));
        Assertions.assertEquals(USER, service.updateUser(ID, USER), NOT_EXPECTED_RESULT);
    }*/
}
