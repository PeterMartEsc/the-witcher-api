package petermartesc.springboot.service.interfaces;

import petermartesc.springboot.exception.ResourceNotFoundException;
import petermartesc.springboot.model.Character;

import java.util.List;

public interface ICharacterService {
    List<Character> getAllCharacters();
    Character getCharacterById(int characterId) throws ResourceNotFoundException;
    public Character createCharacter(Character character);
    Character updateCharacter(int characterId, Character characterDetails) throws ResourceNotFoundException;
    void deleteCharacter(int CharacterId) throws ResourceNotFoundException;
}
