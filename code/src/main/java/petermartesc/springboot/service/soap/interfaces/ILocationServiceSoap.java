package petermartesc.springboot.service.soap.interfaces;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import petermartesc.springboot.exception.ResourceNotFoundException;
import petermartesc.springboot.model.Location;

import java.util.List;

@WebService(targetNamespace = "springboot.service.soap")
public interface ILocationServiceSoap {

    @WebMethod
    @WebResult(
            name="location")
    List<Location> getAllLocations();

    @WebMethod
    Location getLocationById(@WebParam(name = "locationId") int locationId) throws ResourceNotFoundException;

    @WebMethod
    boolean createLocation(@WebParam(name = "location") Location location);
    @WebMethod
    boolean updateLocation(@WebParam(name = "location") Location location, @WebParam(name = "locationId") int locationId) throws ResourceNotFoundException;

    @WebMethod
    boolean deleteLocationById(@WebParam(name = "locationId") int locationId) throws ResourceNotFoundException;
}
