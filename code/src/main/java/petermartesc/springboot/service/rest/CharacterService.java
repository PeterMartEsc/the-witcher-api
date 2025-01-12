package petermartesc.springboot.service.rest;

import petermartesc.springboot.exception.ResourceNotFoundException;
import petermartesc.springboot.model.Character;
import org.springframework.beans.factory.annotation.Autowired;
import petermartesc.springboot.repository.CharacterRepository;
import petermartesc.springboot.service.rest.interfaces.ICharacterService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component
public class CharacterService implements ICharacterService {

    private CharacterRepository characterRepository;

    @Autowired
    public void setCharacterRepository(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @Override
    public List<Character> getAllCharacters() {
        return characterRepository.findAll();
    }

    @Override
    public Character getCharacterById( @PathVariable(value = "id")int characterId) throws ResourceNotFoundException {
        return characterRepository.findById(characterId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + characterId));
    }

    @Override
    public Character createCharacter(@Valid @RequestBody Character character) {
        return characterRepository.save(character);
    }

    @Override
    public Character updateCharacter(@PathVariable(value = "id") int characterId, @Valid @RequestBody Character characterDetails) throws ResourceNotFoundException {
        Character character = characterRepository.findById(characterId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + characterId));

        character.setName(characterDetails.getName());
        return characterRepository.save(character);
    }

    @Override
    public void deleteCharacter(@PathVariable(value = "id") int characterId) throws ResourceNotFoundException {
        Character character = characterRepository.findById(characterId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + characterId));

        characterRepository.delete(character);
    }
}
