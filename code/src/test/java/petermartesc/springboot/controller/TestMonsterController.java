package petermartesc.springboot.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import petermartesc.springboot.exception.ResourceNotFoundException;
import petermartesc.springboot.model.Monster;
import petermartesc.springboot.service.rest.MonsterService;
import petermartesc.springboot.utilities.Utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class TestMonsterController extends Utilities {

    @Mock
    MonsterService mockMonsterService;

    @InjectMocks
    MonsterController controller;

    @BeforeEach
    public void beforeEach (){
        MockitoAnnotations.openMocks(this);
        controller = new MonsterController();
        controller.setMonsterService(mockMonsterService);
    }

    @Test
    void getAllTest() {
        List<Monster> list = new ArrayList<>();
        list.add(new Monster(1, "Ejemplo Nombre","Ejemplo dificultad"));
        list.add(new Monster(1, "Ejemplo Nombre","Ejemplo dificultad"));

        when(mockMonsterService.getAllMonsters()).thenReturn(list);
        Assertions.assertNotNull(controller.getAllMonsters(), NOT_EXPECTED_RESULT);
    }

    @Test
    void getOneTest() throws ResourceNotFoundException {

        when(mockMonsterService.getMonsterById(1)).thenReturn(new Monster(1, "Ejemplo Nombre","Ejemplo dificultad"));
        Assertions.assertNotNull(controller.getMonsterById(1), NOT_EXPECTED_RESULT);
    }

    @Test
    void addTest() {
        Monster monster = new Monster(1, "Ejemplo Nombre","Ejemplo dificultad");

        when(mockMonsterService.createMonster(any(Monster.class))).thenReturn(monster);
        Monster characterResponse = controller.createMonster(monster);
        Assertions.assertEquals(monster, characterResponse, NOT_EXPECTED_RESULT);
    }

    @Test
    void deleteTest() throws ResourceNotFoundException {
        Map<String, Boolean> expectedResponse = new HashMap<>();
        expectedResponse.put("deleted", Boolean.TRUE);

        Map<String, Boolean> responseEntity = controller.deleteMonster(1);
        Assertions.assertEquals(expectedResponse, responseEntity, NOT_EXPECTED_RESULT);
    }

    @Test
    void updateTest() throws ResourceNotFoundException {
        Monster monster = new Monster(2, "Ejemplo Nombre","Ejemplo dificultad");

        when(mockMonsterService.updateMonster(2, monster)).thenReturn(monster);

        ResponseEntity responseEntity = controller.updateMonster(2, monster);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode(), NOT_EXPECTED_RESULT);
    }
}
