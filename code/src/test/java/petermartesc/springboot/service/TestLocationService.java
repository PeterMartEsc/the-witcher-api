package petermartesc.springboot.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import petermartesc.springboot.exception.ResourceNotFoundException;
import petermartesc.springboot.model.Location;
import petermartesc.springboot.repository.LocationRepository;
import petermartesc.springboot.service.rest.LocationService;
import petermartesc.springboot.utilities.Utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class TestLocationService extends Utilities {

    @Mock
    LocationRepository repositoryLocationMock;

    @InjectMocks
    LocationService locationService;

    /*@InjectMocks
    LocationService locationService;*/

    @BeforeEach
    public void beforeEach (){
        MockitoAnnotations.openMocks(this);
        locationService = new LocationService();
        locationService.setLocationRepository(repositoryLocationMock);
    }
    @Test
    void getAllTest() {
        List<Location> list = new ArrayList<>();
        list.add(new Location(3, NAME, KINGDOM));
        list.add(new Location(4, NAME, KINGDOM));
        when(repositoryLocationMock.findAll()).thenReturn(list);
        Assertions.assertNotNull(locationService.getAllLocations(), NOT_EXPECTED_RESULT);
    }


    @Test
    void getOneTest() throws ResourceNotFoundException {
        Location location = new Location(1, NAME, KINGDOM);
        when(repositoryLocationMock.findById(1)).thenReturn(Optional.of(location));
        Assertions.assertNotNull(locationService.getLocationById(1), NOT_EXPECTED_RESULT);
    }



    @Test
    void updateTest() throws ResourceNotFoundException {
        Location location = new Location(1, NAME, KINGDOM);
        when(repositoryLocationMock.findById(1)).thenReturn(Optional.of(location));
        when(repositoryLocationMock.save(any(Location.class))).thenReturn(location);
        //System.out.println(USER);
        Assertions.assertEquals(location, locationService.updateLocation(ID, location), NOT_EXPECTED_RESULT);
    }

    @Test
    void zaddTest() throws ResourceNotFoundException {
        Location location = new Location(1, NAME, KINGDOM);
        when(repositoryLocationMock.save(any(Location.class))).thenReturn(location);
        Assertions.assertEquals(location, locationService.createLocation(location), NOT_EXPECTED_RESULT);
    }

    @Test
    void deleteTest() throws ResourceNotFoundException {
        Location location = new Location(1, NAME, KINGDOM);
        when(repositoryLocationMock.findById(1)).thenReturn(Optional.of(location));
        doNothing().when(repositoryLocationMock).delete(isA(Location.class));
        locationService.deleteLocation(1);
        verify(repositoryLocationMock, times(1)).delete(location);

    }
}
