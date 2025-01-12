package petermartesc.springboot.service.soap;

import jakarta.xml.ws.WebServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import petermartesc.springboot.exception.ResourceNotFoundException;
import petermartesc.springboot.model.Character;
import petermartesc.springboot.service.rest.interfaces.ICharacterService;
import petermartesc.springboot.service.soap.interfaces.ICharacterServiceSoap;

import java.util.List;

public class CharacterServiceSoap implements ICharacterServiceSoap {

    private ICharacterService characterService;

    @Autowired
    public void setCharacterRepository(ICharacterService characterService) {
        this.characterService = characterService;
    }

    @Override
    public List<Character> getAllCharacters() {
        return characterService.getAllCharacters();
    }

    @Override
    public Character getCharacterById(int characterId) {
        try {
            return characterService.getCharacterById(characterId);
        } catch (ResourceNotFoundException e) {
            //Devuelve un error al obtener para no dar más información, no se especifica si existe o no
            throw new WebServiceException("Error obteniendo el usuario", e);
        }
    }

    @Override
    public boolean createCharacter(Character character) {
        if(character == null){
            return false;
        }
        characterService.createCharacter(character);
        return true;
    }

    @Override
    public boolean updateCharacter(Character character, int characterId) {
        try {

            characterService.updateCharacter(characterId, character);

        } catch (ResourceNotFoundException e) {
            throw new WebServiceException("Error al actualizar el usuario", e);
        }

        return true;
    }

    @Override
    public boolean deleteCharacterById(int characterId) {
        try {
            characterService.deleteCharacter(characterId);
            return true;
        } catch (ResourceNotFoundException e) {
            //Devuelve un error al obtener para no dar más información, no se especifica si existe o no
            throw new WebServiceException("Error eliminar el usuario", e);
        }
    }
}
