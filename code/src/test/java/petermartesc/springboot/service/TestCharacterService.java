package petermartesc.springboot.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import petermartesc.springboot.exception.ResourceNotFoundException;
import petermartesc.springboot.model.Character;
import petermartesc.springboot.repository.CharacterRepository;
import petermartesc.springboot.service.rest.CharacterService;
import petermartesc.springboot.utilities.Utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class TestCharacterService extends Utilities {

    @Mock
    CharacterRepository repositoryCharacterMock;

    @InjectMocks
    CharacterService characterService;

    /*@InjectMocks
    CharacterService characterService;*/

    @BeforeEach
    public void beforeEach (){
        MockitoAnnotations.openMocks(this);
        characterService = new CharacterService();
        characterService.setCharacterRepository(repositoryCharacterMock);
    }
    @Test
    void getAllTest() {
        List<Character> list = new ArrayList<>();
        list.add(new Character(3, NAME, SURNAME, DESCRIPTION));
        list.add(new Character(4, NAME, SURNAME, DESCRIPTION));
        when(repositoryCharacterMock.findAll()).thenReturn(list);
        Assertions.assertNotNull(characterService.getAllCharacters(), NOT_EXPECTED_RESULT);
    }


    @Test
    void getOneTest() throws ResourceNotFoundException {
        Character character = new Character(1, NAME, SURNAME, DESCRIPTION);
        when(repositoryCharacterMock.findById(1)).thenReturn(Optional.of(character));
        Assertions.assertNotNull(characterService.getCharacterById(1), NOT_EXPECTED_RESULT);
    }



    @Test
    void updateTest() throws ResourceNotFoundException {
        Character character = new Character(1, NAME, SURNAME, DESCRIPTION);
        when(repositoryCharacterMock.findById(1)).thenReturn(Optional.of(character));
        when(repositoryCharacterMock.save(any(Character.class))).thenReturn(character);
        //System.out.println(USER);
        Assertions.assertEquals(character, characterService.updateCharacter(ID, character), NOT_EXPECTED_RESULT);
    }

    @Test
    void zaddTest() throws ResourceNotFoundException {
        Character character = new Character(1, NAME, SURNAME, DESCRIPTION);
        when(repositoryCharacterMock.save(any(Character.class))).thenReturn(character);
        Assertions.assertEquals(character, characterService.createCharacter(character), NOT_EXPECTED_RESULT);
    }

    @Test
    void deleteTest() throws ResourceNotFoundException {
        Character character = new Character(1, NAME, SURNAME, DESCRIPTION);
        when(repositoryCharacterMock.findById(1)).thenReturn(Optional.of(character));
        doNothing().when(repositoryCharacterMock).delete(isA(Character.class));
        characterService.deleteCharacter(1);
        verify(repositoryCharacterMock, times(1)).delete(character);

    }
}
