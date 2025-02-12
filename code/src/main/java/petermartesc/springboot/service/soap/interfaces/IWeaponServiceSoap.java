package petermartesc.springboot.service.soap.interfaces;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import petermartesc.springboot.exception.ResourceNotFoundException;
import petermartesc.springboot.model.Weapon;

import java.util.List;

@WebService(targetNamespace = "springboot.service.soap")
public interface IWeaponServiceSoap {

    @WebMethod
    @WebResult(
            name="weapon")
    List<Weapon> getAllWeapons();

    @WebMethod
    Weapon getWeaponById(@WebParam(name = "weaponId") int weaponId) throws ResourceNotFoundException;

    @WebMethod
    boolean createWeapon(@WebParam(name = "weapon") Weapon weapon);

    @WebMethod
    boolean updateWeapon(@WebParam(name = "weapon") Weapon weapon, @WebParam(name = "weaponId") int weaponId) throws ResourceNotFoundException;

    @WebMethod
    boolean deleteWeaponById(@WebParam(name = "weaponId") int weaponId) throws ResourceNotFoundException;
}
