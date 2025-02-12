package petermartesc.springboot.service.soap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import petermartesc.springboot.exception.ResourceNotFoundException;
import petermartesc.springboot.model.Location;
import petermartesc.springboot.service.rest.LocationService;
import petermartesc.springboot.utilities.Utilities;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class TestLocationSoapService extends Utilities {

    @Mock
    LocationService repositoryLocationMock;


    @InjectMocks
    LocationServiceSoap locationSoapService;


    @BeforeEach
    public void beforeEach (){
        MockitoAnnotations.openMocks(this);
        locationSoapService = new LocationServiceSoap();
        locationSoapService.setLocationRepository(repositoryLocationMock);
    }
    @Test
    void getAllTest() {
        List<Location> list = new ArrayList<>();
        list.add(LOCATION);
        list.add(LOCATION);
        when(repositoryLocationMock.getAllLocations()).thenReturn(list);
        Assertions.assertNotNull(locationSoapService.getAllLocations(), NOT_EXPECTED_RESULT);
    }


    @Test
    void getOneTest() throws ResourceNotFoundException {
        when(repositoryLocationMock.getLocationById(1)).thenReturn(LOCATION);
        Assertions.assertNotNull(locationSoapService.getLocationById(1), NOT_EXPECTED_RESULT);
    }



    @Test
    void updateTest() throws ResourceNotFoundException {
        //User user = new User(NAME, ROLE);
        when(repositoryLocationMock.getLocationById(1)).thenReturn(LOCATION);
        when(repositoryLocationMock.updateLocation(ID, LOCATION)).thenReturn(LOCATION);
        //System.out.println(USER);
        Assertions.assertTrue( locationSoapService.updateLocation( LOCATION, 1), NOT_EXPECTED_RESULT);
    }

    @Test
    void zaddTest() throws ResourceNotFoundException {
        when(repositoryLocationMock.createLocation(any(Location.class))).thenReturn(LOCATION);
        Assertions.assertTrue(locationSoapService.createLocation(LOCATION), NOT_EXPECTED_RESULT);
    }

    @Test
    void deleteTest() throws ResourceNotFoundException {
        Location location = new Location(1, NAME, KINGDOM);
        when(repositoryLocationMock.getLocationById(1)).thenReturn(location);
        doNothing().when(repositoryLocationMock).deleteLocation(isA(Integer.class));
        locationSoapService.deleteLocationById(1);
        verify(repositoryLocationMock, times(1)).deleteLocation(1);
    }
}
