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
import petermartesc.springboot.model.Alchemy;
import petermartesc.springboot.service.rest.AlchemyService;
import petermartesc.springboot.utilities.Utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class TestAlchemysController extends Utilities {

    @Mock
    AlchemyService mockAlchemyService;

    @InjectMocks
    AlchemyController controller;

    @BeforeEach
    public void beforeEach (){
        MockitoAnnotations.openMocks(this);
        controller = new AlchemyController();
        controller.setAlchemyService(mockAlchemyService);
    }

    @Test
    void getAllTest() {
        List<Alchemy> list = new ArrayList<>();
        list.add(new Alchemy(1, "Alp Fangs", "Eter"));
        list.add(new Alchemy(2, "Cadaverine", "Rebis"));

        when(mockAlchemyService.getAllAlchemys()).thenReturn(list);
        Assertions.assertNotNull(controller.getAllAlchemys(), NOT_EXPECTED_RESULT);
    }

    @Test
    void getOneTest() throws ResourceNotFoundException {

        when(mockAlchemyService.getAlchemyById(1)).thenReturn(new Alchemy(1, "Ejemplo", "Ejemplo ALchemies"));
        Assertions.assertNotNull(controller.getAlchemyById(1), NOT_EXPECTED_RESULT);
    }

    @Test
    void addTest() {
        Alchemy alchemy = new Alchemy(3, "Ejemplo", "Ejemplo Alchemies");

        when(mockAlchemyService.createAlchemy(any(Alchemy.class))).thenReturn(alchemy);
        Alchemy alchemyResponse = controller.createAlchemy(alchemy);
        Assertions.assertEquals(alchemy, alchemyResponse, NOT_EXPECTED_RESULT);
    }

    @Test
    void deleteTest() throws ResourceNotFoundException {
        Map<String, Boolean> expectedResponse = new HashMap<>();
        expectedResponse.put("deleted", Boolean.TRUE);

        Map<String, Boolean> responseEntity = controller.deleteAlchemy(1);
        Assertions.assertEquals(expectedResponse, responseEntity, NOT_EXPECTED_RESULT);
    }

    @Test
    void updateTest() throws ResourceNotFoundException {
        Alchemy alchemy = new Alchemy(2, "Ejemplo", "Ejemplo material");

        when(mockAlchemyService.updateAlchemy(2, alchemy)).thenReturn(alchemy);

        ResponseEntity responseEntity = controller.updateAlchemy(2, alchemy);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode(), NOT_EXPECTED_RESULT);
    }
}
