package petermartesc.springboot.controller;

import jdk.jshell.execution.Util;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import petermartesc.springboot.exception.ResourceNotFoundException;
import petermartesc.springboot.model.Character;
import petermartesc.springboot.service.rest.CharacterService;
import petermartesc.springboot.utilities.Utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class TestCharacterController extends Utilities {

    @Mock
    CharacterService mockCharacterService;

    @InjectMocks
    CharacterController controller;

    @BeforeEach
    public void beforeEach (){
        MockitoAnnotations.openMocks(this);
        controller = new CharacterController();
        controller.setCharacterService(mockCharacterService);
    }

    @Test
    void getAllTest() {
        List<Character> list = new ArrayList<>();
        list.add(new Character(1, "Ejemplo Nombre","Ejemplo apellidos","Ejemplo descripcion"));
        list.add(new Character(1, "Ejemplo Nombre","Ejemplo apellidos","Ejemplo descripcion"));

        when(mockCharacterService.getAllCharacters()).thenReturn(list);
        Assertions.assertNotNull(controller.getAllCharacters(), NOT_EXPECTED_RESULT);
    }

    @Test
    void getOneTest() throws ResourceNotFoundException {

        when(mockCharacterService.getCharacterById(1)).thenReturn(new Character(1, "Ejemplo Nombre","Ejemplo apellidos","Ejemplo descripcion"));
        Assertions.assertNotNull(controller.getCharacterById(1), NOT_EXPECTED_RESULT);
    }

    @Test
    void addTest() {
        Character character = new Character(1, "Ejemplo Nombre","Ejemplo apellidos","Ejemplo descripcion");

        when(mockCharacterService.createCharacter(any(Character.class))).thenReturn(character);
        Character characterResponse = controller.createCharacter(character);
        Assertions.assertEquals(character, characterResponse, NOT_EXPECTED_RESULT);
    }

    @Test
    void deleteTest() throws ResourceNotFoundException {
        Map<String, Boolean> expectedResponse = new HashMap<>();
        expectedResponse.put("deleted", Boolean.TRUE);

        Map<String, Boolean> responseEntity = controller.deleteCharacter(1);
        Assertions.assertEquals(expectedResponse, responseEntity, NOT_EXPECTED_RESULT);
    }

    @Test
    void updateTest() throws ResourceNotFoundException {
        Character character = new Character(2, "Ejemplo Nombre","Ejemplo apellidos","Ejemplo descripcion");

        when(mockCharacterService.updateCharacter(2, character)).thenReturn(character);

        ResponseEntity responseEntity = controller.updateCharacter(2, character);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode(), NOT_EXPECTED_RESULT);
    }
}
