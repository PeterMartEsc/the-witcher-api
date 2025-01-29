package petermartesc.springboot.service.soap.interfaces;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import petermartesc.springboot.exception.ResourceNotFoundException;
import petermartesc.springboot.model.User;

import java.util.List;

@WebService(targetNamespace = "springboot.service.soap")
public interface IUserServiceSoap {
    @WebMethod
    @WebResult(
            name="user")
    List<User> getAllUsers();

    @WebMethod
    User getUserById(@WebParam(name = "userId") int userId);

    @WebMethod
    boolean createUser(@WebParam(name = "user") User user) throws ResourceNotFoundException;
    @WebMethod
    boolean updateUser(@WebParam(name = "user") User user, @WebParam(name = "userId") int userId);

    @WebMethod
    boolean deleteUserById(@WebParam(name = "userId") int userId);
}
