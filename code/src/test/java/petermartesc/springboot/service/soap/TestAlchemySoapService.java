package petermartesc.springboot.service.soap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import petermartesc.springboot.exception.ResourceNotFoundException;
import petermartesc.springboot.model.Alchemy;
import petermartesc.springboot.service.soap.interfaces.IAlchemyServiceSoap;
import petermartesc.springboot.utilities.Utilities;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class TestAlchemySoapService extends Utilities {

    @Mock
    IAlchemyServiceSoap repositoryAlchemyMock;

    @InjectMocks
    AlchemyServiceSoap alchemyService;

    /*@InjectMocks
    AlchemyService alchemyService;*/

    @BeforeEach
    public void beforeEach (){
        MockitoAnnotations.openMocks(this);
        alchemyService = new AlchemyServiceSoap();
        alchemyService.setAlchemyRepository(repositoryAlchemyMock);
    }
    @Test
    void getAllTest() {
        List<Alchemy> list = new ArrayList<>();
        list.add(new Alchemy(3, NAME, MATERIAL));
        list.add(new Alchemy(4, NAME, MATERIAL));
        when(repositoryAlchemyMock.getAllAlchemys()).thenReturn(list);
        Assertions.assertNotNull(alchemyService.getAllAlchemys(), NOT_EXPECTED_RESULT);
    }


    @Test
    void getOneTest() throws ResourceNotFoundException {
        when(repositoryAlchemyMock.getAlchemyById(1)).thenReturn(new Alchemy(1, NAME, MATERIAL));
        Assertions.assertNotNull(alchemyService.getAlchemyById(1), NOT_EXPECTED_RESULT);
    }



    @Test
    void updateTest() throws ResourceNotFoundException {
        //User user = new User(NAME, ROLE);
        when(repositoryAlchemyMock.getAlchemyById(1)).thenReturn(new Alchemy(1, NAME, MATERIAL));
        when(repositoryAlchemyMock.updateAlchemy(new Alchemy(3, NAME, MATERIAL), ID)).thenReturn(true);
        //System.out.println(USER);
        Assertions.assertTrue(alchemyService.updateAlchemy(new Alchemy(3, NAME, MATERIAL), 1), NOT_EXPECTED_RESULT);
    }

    @Test
    void zaddTest() throws ResourceNotFoundException {
        when(repositoryAlchemyMock.createAlchemy(any(Alchemy.class))).thenReturn(true);
        Assertions.assertTrue(alchemyService.createAlchemy(new Alchemy(3, NAME, MATERIAL)), NOT_EXPECTED_RESULT);
    }

    @Test
    void deleteTest() throws ResourceNotFoundException {
        when(repositoryAlchemyMock.deleteAlchemyById(1)).thenReturn(true);
        Assertions.assertTrue(alchemyService.deleteAlchemyById(ID), NOT_EXPECTED_RESULT);
    }
}
