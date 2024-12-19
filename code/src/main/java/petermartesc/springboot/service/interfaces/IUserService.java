package petermartesc.springboot.service.interfaces;

import petermartesc.springboot.exception.ResourceNotFoundException;
import petermartesc.springboot.model.User;

import java.util.List;

public interface IUserService {
    List<User> getAllUsers();
    User getUserById(int userId) throws ResourceNotFoundException;
    public User createUser(User user);
    User updateUser(int userId, User userDetails) throws ResourceNotFoundException;
    void deleteUser(int userId) throws ResourceNotFoundException;
}
