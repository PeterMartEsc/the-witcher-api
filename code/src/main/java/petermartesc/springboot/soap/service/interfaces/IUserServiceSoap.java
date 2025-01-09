package petermartesc.springboot.soap.service.interfaces;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import petermartesc.springboot.model.User;

import java.util.List;

@WebService(targetNamespace = "springboot.soap.service")
public interface IUserServiceSoap {
    @WebMethod
    @WebResult(
            name="user")
    List<User> getAllUsers();

    @WebMethod
    User getUserById(@WebParam(name = "userId") int userId);


}
