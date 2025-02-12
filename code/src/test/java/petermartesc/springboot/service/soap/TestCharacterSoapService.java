package petermartesc.springboot.service.soap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import petermartesc.springboot.exception.ResourceNotFoundException;
import petermartesc.springboot.model.Character;
import petermartesc.springboot.service.rest.CharacterService;
import petermartesc.springboot.utilities.Utilities;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class TestCharacterSoapService extends Utilities {

    @Mock
    CharacterService repositoryCharacterMock;

    @InjectMocks
    CharacterServiceSoap characterSoapService;

    @BeforeEach
    public void beforeEach (){
        MockitoAnnotations.openMocks(this);
        characterSoapService = new CharacterServiceSoap();
        characterSoapService.setCharacterRepository(repositoryCharacterMock);
    }


    @Test
    void getAllTest() {
        List<Character> list = new ArrayList<>();
        list.add(CHARACTER);
        list.add(CHARACTER);
        when(repositoryCharacterMock.getAllCharacters()).thenReturn(list);
        Assertions.assertNotNull(characterSoapService.getAllCharacters(), NOT_EXPECTED_RESULT);
    }


    @Test
    void getOneTest() throws ResourceNotFoundException {
        when(repositoryCharacterMock.getCharacterById(1)).thenReturn(CHARACTER);
        Assertions.assertNotNull(characterSoapService.getCharacterById(1), NOT_EXPECTED_RESULT);
    }


    @Test
    void updateTest() throws ResourceNotFoundException {
        //User user = new User(NAME, CHARACTER);
        when(repositoryCharacterMock.getCharacterById(1)).thenReturn(CHARACTER);
        when(repositoryCharacterMock.updateCharacter(ID, CHARACTER)).thenReturn(CHARACTER);
        //System.out.println(USER);
        Assertions.assertTrue(characterSoapService.updateCharacter(CHARACTER, ID), NOT_EXPECTED_RESULT);
    }


    @Test
    void zaddTest() throws ResourceNotFoundException {
        when(repositoryCharacterMock.createCharacter(any(Character.class))).thenReturn(CHARACTER);
        Assertions.assertTrue(characterSoapService.createCharacter(CHARACTER), NOT_EXPECTED_RESULT);
    }


    @Test
    void deleteTest() throws ResourceNotFoundException {
        Character character = new Character(1, NAME, SURNAME, DESCRIPTION);
        when(repositoryCharacterMock.getCharacterById(1)).thenReturn(character);
        doNothing().when(repositoryCharacterMock).deleteCharacter(isA(Integer.class));
        characterSoapService.deleteCharacterById(1);
        verify(repositoryCharacterMock, times(1)).deleteCharacter(1);
    }
}
