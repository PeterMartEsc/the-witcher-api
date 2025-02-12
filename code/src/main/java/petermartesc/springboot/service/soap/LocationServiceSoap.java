package petermartesc.springboot.service.soap;

import jakarta.xml.ws.WebServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import petermartesc.springboot.exception.ResourceNotFoundException;
import petermartesc.springboot.model.Location;
import petermartesc.springboot.service.rest.interfaces.ILocationService;
import petermartesc.springboot.service.soap.interfaces.ILocationServiceSoap;

import java.util.List;

public class LocationServiceSoap implements ILocationServiceSoap {

    private ILocationService locationService;

    @Autowired
    public void setLocationRepository(ILocationService locationService) {
        this.locationService = locationService;
    }

    @Override
    public List<Location> getAllLocations() {
        return locationService.getAllLocations();
    }

    @Override
    public Location getLocationById(int locationId) throws ResourceNotFoundException {
        return locationService.getLocationById(locationId);
    }

    @Override
    public boolean createLocation(Location location) {
        if(location == null){
            return false;
        }
        locationService.createLocation(location);
        return true;
    }

    @Override
    public boolean updateLocation(Location location, int locationId) throws ResourceNotFoundException {
        locationService.updateLocation(locationId, location);
        return true;
    }

    @Override
    public boolean deleteLocationById(int locationId) throws ResourceNotFoundException {
        locationService.deleteLocation(locationId);
        return true;
    }
}
