package es.ies.puerto.petermartesc.springboot.service.interfaces;

import es.ies.puerto.petermartesc.springboot.exception.ResourceNotFoundException;
import es.ies.puerto.petermartesc.springboot.model.Character;

import java.util.List;

public interface ICharacterService {
    List<Character> getAllCharacters();
    Character getCharacterById(int characterId) throws ResourceNotFoundException;
    Character createCharacter(Character character);
    Character updateCharacter(int characterId, Character characterDetails) throws ResourceNotFoundException;
    void deleteCharacter(int CharacterId) throws ResourceNotFoundException;
}
