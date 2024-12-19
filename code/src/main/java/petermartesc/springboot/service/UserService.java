package petermartesc.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;


import jakarta.validation.Valid;
import petermartesc.springboot.exception.ResourceNotFoundException;
import petermartesc.springboot.model.User;
import petermartesc.springboot.repository.UserRepository;
import petermartesc.springboot.service.interfaces.IUserService;

@Component
public class UserService implements IUserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(@PathVariable(value = "id") int userId) throws ResourceNotFoundException {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
    }

    public User createUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    public User updateUser(@PathVariable(value = "id") int userId, @Valid @RequestBody User userDetails) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

        user.setName(userDetails.getName());
        return userRepository.save(user);
    }

    public void deleteUser(@PathVariable(value = "id") int userId) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

        userRepository.delete(user);
    }
    
}
