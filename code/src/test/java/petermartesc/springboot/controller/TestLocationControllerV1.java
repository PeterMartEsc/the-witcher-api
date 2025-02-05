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
import petermartesc.springboot.model.Location;
import petermartesc.springboot.service.rest.LocationService;
import petermartesc.springboot.utilities.Utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class TestLocationControllerV1 extends Utilities {

    @Mock
    LocationService mockLocationService;

    @InjectMocks
    LocationControllerV1 controller;

    @BeforeEach
    public void beforeEach (){
        MockitoAnnotations.openMocks(this);
        controller = new LocationControllerV1();
        controller.setLocationService(mockLocationService);
    }

    @Test
    void getAllTest() {
        List<Location> list = new ArrayList<>();
        list.add(new Location(1, "Ejemplo Nombre","Ejemplo reino"));
        list.add(new Location(1, "Ejemplo Nombre","Ejemplo reino"));

        when(mockLocationService.getAllLocations()).thenReturn(list);
        Assertions.assertNotNull(controller.getAllLocations(), NOT_EXPECTED_RESULT);
    }

    @Test
    void getOneTest() throws ResourceNotFoundException {

        when(mockLocationService.getLocationById(1)).thenReturn(new Location(1, "Ejemplo Nombre","Ejemplo reino"));
        Assertions.assertNotNull(controller.getLocationById(1), NOT_EXPECTED_RESULT);
    }

    @Test
    void addTest() {
        Location location = new Location(1, "Ejemplo Nombre","Ejemplo reino");

        when(mockLocationService.createLocation(any(Location.class))).thenReturn(location);
        Location characterResponse = controller.createLocation(location);
        Assertions.assertEquals(location, characterResponse, NOT_EXPECTED_RESULT);
    }

    @Test
    void deleteTest() throws ResourceNotFoundException {
        Map<String, Boolean> expectedResponse = new HashMap<>();
        expectedResponse.put("deleted", Boolean.TRUE);

        Map<String, Boolean> responseEntity = controller.deleteLocation(1);
        Assertions.assertEquals(expectedResponse, responseEntity, NOT_EXPECTED_RESULT);
    }

    @Test
    void updateTest() throws ResourceNotFoundException {
        Location location = new Location(2, "Ejemplo Nombre","Ejemplo reino");

        when(mockLocationService.updateLocation(2, location)).thenReturn(location);

        ResponseEntity responseEntity = controller.updateLocation(2, location);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode(), NOT_EXPECTED_RESULT);
    }
}
