package petermartesc.springboot.soap.service;

import org.springframework.beans.factory.annotation.Autowired;
import petermartesc.springboot.model.User;
import petermartesc.springboot.service.interfaces.IUserService;
import petermartesc.springboot.soap.service.interfaces.IUserServiceSoap;

import java.util.List;

public class UserServiceSoap implements IUserServiceSoap {

    private IUserService userService;

    @Autowired
    public void setUserRepository(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public User getUserById(int userId) {
        return null;
    }
}
