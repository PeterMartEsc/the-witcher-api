package es.ies.puerto.petermartesc.springboot.service.interfaces;

import es.ies.puerto.petermartesc.springboot.exception.ResourceNotFoundException;
import es.ies.puerto.petermartesc.springboot.model.User;

import java.util.List;

public interface IUserService {
    List<User> getAllUsers();
    User getUserById(int userId) throws ResourceNotFoundException;
    public User createUser(User user);
    User updateUser(int userId, User userDetails) throws ResourceNotFoundException;
    void deleteUser(int userId) throws ResourceNotFoundException;
}
