package petermartesc.springboot.service.soap.interfaces;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import petermartesc.springboot.exception.ResourceNotFoundException;
import petermartesc.springboot.model.Alchemy;

import java.util.List;

@WebService(targetNamespace = "petermartesc.springboot.service.soap.interfaces")
public interface IAlchemyServiceSoap {

    @WebMethod
    @WebResult(
            name="alchemy")
    List<Alchemy> getAllAlchemys();

    @WebMethod
    Alchemy getAlchemyById(@WebParam(name = "alchemyId") int alchemyId) throws ResourceNotFoundException;

    @WebMethod
    boolean createAlchemy(@WebParam(name = "alchemy") Alchemy alchemy);
    @WebMethod
    boolean updateAlchemy(@WebParam(name = "alchemy") Alchemy alchemy, @WebParam(name = "alchemyId") int alchemyId);

    @WebMethod
    boolean deleteAlchemyById(@WebParam(name = "alchemyId") int alchemyId);
}
