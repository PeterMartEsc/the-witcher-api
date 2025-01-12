package petermartesc.springboot.service.soap.interfaces;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import petermartesc.springboot.model.Monster;

import java.util.List;

@WebService(targetNamespace = "springboot.service.soap")
public interface IMonsterServiceSoap {

    @WebMethod
    @WebResult(
            name="monster")
    List<Monster> getAllMonsters();

    @WebMethod
    Monster getMonsterById(@WebParam(name = "monsterId") int monsterId);

    @WebMethod
    boolean createMonster(@WebParam(name = "monster") Monster monster);
    @WebMethod
    boolean updateMonster(@WebParam(name = "monster") Monster monster, @WebParam(name = "monsterId") int monsterId);

    @WebMethod
    boolean deleteMonsterById(@WebParam(name = "monsterId") int monsterId);
}
