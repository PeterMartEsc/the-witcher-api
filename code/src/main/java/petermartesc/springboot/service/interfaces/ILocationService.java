package petermartesc.springboot.service.interfaces;

import petermartesc.springboot.exception.ResourceNotFoundException;
import petermartesc.springboot.model.Location;

import java.util.List;

public interface ILocationService {

    List<Location> getAllLocations();
    Location getLocationById(int locationId) throws ResourceNotFoundException;
    public Location createLocation(Location location);
    Location updateLocation(int locationId, Location locationDetails) throws ResourceNotFoundException;
    void deleteLocation(int locationId) throws ResourceNotFoundException;
}
