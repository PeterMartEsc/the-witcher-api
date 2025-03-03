package petermartesc.springboot.service.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;


import jakarta.validation.Valid;
import petermartesc.springboot.exception.ResourceNotFoundException;
import petermartesc.springboot.model.Role;
import petermartesc.springboot.model.User;
import petermartesc.springboot.repository.RoleRepository;
import petermartesc.springboot.repository.UserRepository;
import petermartesc.springboot.service.rest.interfaces.IUserService;

@Component
public class UserService implements IUserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(@PathVariable(value = "id") int userId) throws ResourceNotFoundException {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
    }
    @Transactional
    public User createUser(@Valid @RequestBody User user) throws ResourceNotFoundException {
        int idRole = user.getRole().getId();
        Role role = roleRepository.findById(idRole).
                orElseThrow(() -> new ResourceNotFoundException("Role asociated not found for this id :: " +idRole));
        user.setRole(role);
        return userRepository.save(user);
    }
    @Transactional
    public User updateUser(@PathVariable(value = "id") int userId, @Valid @RequestBody User userDetails) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

        if(userDetails.getName() != null){
            user.setName(userDetails.getName());
        }
        if(userDetails.getPassword() != null){
            user.setPassword(userDetails.getPassword());
        }
        if(userDetails.getRole() != null){
            Role role = roleRepository.findById(userDetails.getRole().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Role asociated not found for this id :: " +
                            userDetails.getRole().getId()));
            user.setRole(role);
        }

        return userRepository.save(user);
    }

    @Transactional
    public void deleteUser(@PathVariable(value = "id") int userId) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

        userRepository.delete(user);
    }
    
}
