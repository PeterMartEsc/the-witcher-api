package petermartesc.springboot.service.soap.interfaces;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import petermartesc.springboot.model.Character;

import java.util.List;

@WebService(targetNamespace = "springboot.service.soap")
public interface ICharacterServiceSoap {

    @WebMethod
    @WebResult(
            name="character")
    List<Character> getAllCharacters();

    @WebMethod
    Character getCharacterById(@WebParam(name = "characterId") int characterId);

    @WebMethod
    boolean createCharacter(@WebParam(name = "character") Character character);
    @WebMethod
    boolean updateCharacter(@WebParam(name = "character") Character character, @WebParam(name = "characterId") int characterId);

    @WebMethod
    boolean deleteCharacterById(@WebParam(name = "characterId") int characterId);
}
