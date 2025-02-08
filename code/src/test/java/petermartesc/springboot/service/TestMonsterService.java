package petermartesc.springboot.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import petermartesc.springboot.exception.ResourceNotFoundException;
import petermartesc.springboot.model.Monster;
import petermartesc.springboot.repository.MonsterRepository;
import petermartesc.springboot.service.rest.MonsterService;
import petermartesc.springboot.utilities.Utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class TestMonsterService extends Utilities {

    @Mock
    MonsterRepository repositoryMonsterMock;

    @InjectMocks
    MonsterService monsterService;

    /*@InjectMocks
    MonsterService monsterService;*/

    @BeforeEach
    public void beforeEach (){
        MockitoAnnotations.openMocks(this);
        monsterService = new MonsterService();
        monsterService.setMonsterRepository(repositoryMonsterMock);
    }
    @Test
    void getAllTest() {
        List<Monster> list = new ArrayList<>();
        list.add(new Monster(3, NAME, DIFFICULTY));
        list.add(new Monster(4, NAME, DIFFICULTY));
        when(repositoryMonsterMock.findAll()).thenReturn(list);
        Assertions.assertNotNull(monsterService.getAllMonsters(), NOT_EXPECTED_RESULT);
    }


    @Test
    void getOneTest() throws ResourceNotFoundException {
        Monster monster = new Monster(1, NAME, DIFFICULTY);
        when(repositoryMonsterMock.findById(1)).thenReturn(Optional.of(monster));
        Assertions.assertNotNull(monsterService.getMonsterById(1), NOT_EXPECTED_RESULT);
    }



    @Test
    void updateTest() throws ResourceNotFoundException {
        Monster monster = new Monster(1, NAME, DIFFICULTY);
        when(repositoryMonsterMock.findById(1)).thenReturn(Optional.of(monster));
        when(repositoryMonsterMock.save(any(Monster.class))).thenReturn(monster);
        //System.out.println(USER);
        Assertions.assertEquals(monster, monsterService.updateMonster(ID, monster), NOT_EXPECTED_RESULT);
    }

    @Test
    void zaddTest() throws ResourceNotFoundException {
        Monster monster = new Monster(1, NAME, DIFFICULTY);
        when(repositoryMonsterMock.save(any(Monster.class))).thenReturn(monster);
        Assertions.assertEquals(monster, monsterService.createMonster(monster), NOT_EXPECTED_RESULT);
    }

    @Test
    void deleteTest() throws ResourceNotFoundException {
        Monster monster = new Monster(1, NAME, DIFFICULTY);
        when(repositoryMonsterMock.findById(1)).thenReturn(Optional.of(monster));
        doNothing().when(repositoryMonsterMock).delete(isA(Monster.class));
        monsterService.deleteMonster(1);
        verify(repositoryMonsterMock, times(1)).delete(monster);

    }
}
