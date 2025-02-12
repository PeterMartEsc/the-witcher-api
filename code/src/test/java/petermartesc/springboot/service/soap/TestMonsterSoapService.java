package petermartesc.springboot.service.soap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import petermartesc.springboot.exception.ResourceNotFoundException;
import petermartesc.springboot.model.Monster;
import petermartesc.springboot.service.rest.MonsterService;
import petermartesc.springboot.utilities.Utilities;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class TestMonsterSoapService extends Utilities {
    
    @Mock
    MonsterService repositoryMonsterMock;

    @InjectMocks
    MonsterServiceSoap monsterSoapService;


    @BeforeEach
    public void beforeEach (){
        MockitoAnnotations.openMocks(this);
        monsterSoapService = new MonsterServiceSoap();
        monsterSoapService.setMonsterRepository(repositoryMonsterMock);
    }
    @Test
    void getAllTest() {
        List<Monster> list = new ArrayList<>();
        list.add(MONSTER);
        list.add(MONSTER);
        when(repositoryMonsterMock.getAllMonsters()).thenReturn(list);
        Assertions.assertNotNull(monsterSoapService.getAllMonsters(), NOT_EXPECTED_RESULT);
    }


    @Test
    void getOneTest() throws ResourceNotFoundException {
        when(repositoryMonsterMock.getMonsterById(1)).thenReturn(MONSTER);
        Assertions.assertNotNull(monsterSoapService.getMonsterById(1), NOT_EXPECTED_RESULT);
    }



    @Test
    void updateTest() throws ResourceNotFoundException {
        //User user = new User(NAME, ROLE);
        when(repositoryMonsterMock.getMonsterById(1)).thenReturn(MONSTER);
        when(repositoryMonsterMock.updateMonster(ID, MONSTER)).thenReturn(MONSTER);
        //System.out.println(USER);
        Assertions.assertTrue( monsterSoapService.updateMonster( MONSTER, 1), NOT_EXPECTED_RESULT);
    }

    @Test
    void zaddTest() throws ResourceNotFoundException {
        when(repositoryMonsterMock.createMonster(any(Monster.class))).thenReturn(MONSTER);
        Assertions.assertTrue(monsterSoapService.createMonster(MONSTER), NOT_EXPECTED_RESULT);
    }

    @Test
    void deleteTest() throws ResourceNotFoundException {
        Monster monster = new Monster(1, NAME, DIFFICULTY);
        when(repositoryMonsterMock.getMonsterById(1)).thenReturn(monster);
        doNothing().when(repositoryMonsterMock).deleteMonster(isA(Integer.class));
        monsterSoapService.deleteMonsterById(1);
        verify(repositoryMonsterMock, times(1)).deleteMonster(1);
    }
}
