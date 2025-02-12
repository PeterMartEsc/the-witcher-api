package petermartesc.springboot.service.soap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import petermartesc.springboot.exception.ResourceNotFoundException;
import petermartesc.springboot.model.Alchemy;
import petermartesc.springboot.service.rest.AlchemyService;
import petermartesc.springboot.utilities.Utilities;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class TestAlchemySoapService extends Utilities {

    @Mock
    AlchemyService repositoryAlchemyMock;


    @InjectMocks
    AlchemyServiceSoap alchemySoapService;


    @BeforeEach
    public void beforeEach (){
        MockitoAnnotations.openMocks(this);
        alchemySoapService = new AlchemyServiceSoap();
        alchemySoapService.setAlchemyRepository(repositoryAlchemyMock);
    }
    @Test
    void getAllTest() {
        List<Alchemy> list = new ArrayList<>();
        list.add(ALCHEMY);
        list.add(new Alchemy(4, NAME, MATERIAL));
        when(repositoryAlchemyMock.getAllAlchemys()).thenReturn(list);
        Assertions.assertNotNull(alchemySoapService.getAllAlchemys(), NOT_EXPECTED_RESULT);
    }


    @Test
    void getOneTest() throws ResourceNotFoundException {
        when(repositoryAlchemyMock.getAlchemyById(1)).thenReturn(ALCHEMY);
        Assertions.assertNotNull(alchemySoapService.getAlchemyById(1), NOT_EXPECTED_RESULT);
    }



    @Test
    void updateTest() throws ResourceNotFoundException {
        //User user = new User(NAME, ROLE);
        when(repositoryAlchemyMock.getAlchemyById(1)).thenReturn(ALCHEMY);
        when(repositoryAlchemyMock.updateAlchemy(ID, ALCHEMY)).thenReturn(ALCHEMY);
        //System.out.println(USER);
        Assertions.assertTrue( alchemySoapService.updateAlchemy( ALCHEMY, 1), NOT_EXPECTED_RESULT);
    }

    @Test
    void zaddTest() throws ResourceNotFoundException {
        when(repositoryAlchemyMock.createAlchemy(any(Alchemy.class))).thenReturn(ALCHEMY);
        Assertions.assertTrue(alchemySoapService.createAlchemy(ALCHEMY), NOT_EXPECTED_RESULT);
    }

    @Test
    void deleteTest() throws ResourceNotFoundException {
        Alchemy alchemy = new Alchemy(1, NAME, MATERIAL);
        when(repositoryAlchemyMock.getAlchemyById(1)).thenReturn(alchemy);
        doNothing().when(repositoryAlchemyMock).deleteAlchemy(isA(Integer.class));
        alchemySoapService.deleteAlchemyById(1);
        verify(repositoryAlchemyMock, times(1)).deleteAlchemy(1);
    }
}