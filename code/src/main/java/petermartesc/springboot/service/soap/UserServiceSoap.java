package petermartesc.springboot.service.soap;

import jakarta.xml.ws.WebServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import petermartesc.springboot.exception.ResourceNotFoundException;
import petermartesc.springboot.model.User;
import petermartesc.springboot.service.rest.interfaces.IUserService;
import petermartesc.springboot.service.soap.interfaces.IUserServiceSoap;

import java.util.List;

public class UserServiceSoap implements IUserServiceSoap {

    private IUserService userService;

    @Autowired
    public void setUserRepository(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @Override
    public User getUserById(int userId) throws ResourceNotFoundException {
        return userService.getUserById(userId);
    }

    @Override
    public boolean createUser(User user) throws ResourceNotFoundException {
        if(user == null){
            return false;
        }
        userService.createUser(user);
        return true;
    }

    @Override
    public boolean updateUser(User user, int userId) throws ResourceNotFoundException {
        userService.updateUser(userId, user);
        return true;
    }

    @Override
    public boolean deleteUserById(int userId) throws ResourceNotFoundException {
        userService.deleteUser(userId);
        return true;
    }
}
