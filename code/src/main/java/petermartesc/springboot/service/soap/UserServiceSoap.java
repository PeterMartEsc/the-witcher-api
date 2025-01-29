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
    public User getUserById(int userId) {
        try {
            return userService.getUserById(userId);
        } catch (ResourceNotFoundException e) {
            //Devuelve un error al obtener para no dar más información, no se especifica si existe o no
            throw new WebServiceException("Error obteniendo el usuario", e);
        }
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
    public boolean updateUser(User user, int userId) {
        try {
            /**User userToUpdate = userService.getUserById(userId);
            userToUpdate.setName(user.getName());
            userToUpdate.setIdrole(user.getIdrole());**/
            userService.updateUser(userId, user);

        } catch (ResourceNotFoundException e) {
            throw new WebServiceException("Error al actualizar el usuario", e);
        }

        return true;
    }

    @Override
    public boolean deleteUserById(int userId) {
        try {
            userService.deleteUser(userId);
            return true;
        } catch (ResourceNotFoundException e) {
            //Devuelve un error al obtener para no dar más información, no se especifica si existe o no
            throw new WebServiceException("Error eliminar el usuario", e);
        }
    }
}
