package petermartesc.springboot.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import petermartesc.springboot.exception.ResourceNotFoundException;
import petermartesc.springboot.model.Alchemy;
import petermartesc.springboot.repository.AlchemyRepository;
import petermartesc.springboot.service.rest.AlchemyService;
import petermartesc.springboot.utilities.Utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class TestAlchemyService extends Utilities {

    @Mock
    AlchemyRepository repositoryAlchemyMock;

    @InjectMocks
    AlchemyService alchemyService;

    /*@InjectMocks
    AlchemyService alchemyService;*/

    @BeforeEach
    public void beforeEach (){
        MockitoAnnotations.openMocks(this);
        alchemyService = new AlchemyService();
        alchemyService.setAlchemyRepository(repositoryAlchemyMock);
    }
    @Test
    void getAllTest() {
        List<Alchemy> list = new ArrayList<>();
        list.add(new Alchemy(3, NAME, MATERIAL));
        list.add(new Alchemy(4, NAME, MATERIAL));
        when(repositoryAlchemyMock.findAll()).thenReturn(list);
        Assertions.assertNotNull(alchemyService.getAllAlchemys(), NOT_EXPECTED_RESULT);
    }


    @Test
    void getOneTest() throws ResourceNotFoundException {
        Alchemy alchemy = new Alchemy(1, NAME, MATERIAL);
        when(repositoryAlchemyMock.findById(1)).thenReturn(Optional.of(alchemy));
        Assertions.assertNotNull(alchemyService.getAlchemyById(1), NOT_EXPECTED_RESULT);
    }



    @Test
    void updateTest() throws ResourceNotFoundException {
        Alchemy alchemy = new Alchemy(1, NAME, MATERIAL);
        when(repositoryAlchemyMock.findById(1)).thenReturn(Optional.of(alchemy));
        when(repositoryAlchemyMock.save(any(Alchemy.class))).thenReturn(alchemy);
        //System.out.println(USER);
        Assertions.assertEquals(alchemy, alchemyService.updateAlchemy(ID, alchemy), NOT_EXPECTED_RESULT);
    }

    @Test
    void zaddTest() throws ResourceNotFoundException {
        Alchemy alchemy = new Alchemy(1, NAME, MATERIAL);
        when(repositoryAlchemyMock.save(any(Alchemy.class))).thenReturn(alchemy);
        Assertions.assertEquals(alchemy, alchemyService.createAlchemy(alchemy), NOT_EXPECTED_RESULT);
    }

    @Test
    void deleteTest() throws ResourceNotFoundException {
        Alchemy alchemy = new Alchemy(1, NAME, MATERIAL);
        when(repositoryAlchemyMock.findById(1)).thenReturn(Optional.of(alchemy));
        doNothing().when(repositoryAlchemyMock).delete(isA(Alchemy.class));
        alchemyService.deleteAlchemy(1);
        verify(repositoryAlchemyMock, times(1)).delete(alchemy);
    }
}
