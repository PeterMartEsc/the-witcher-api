package petermartesc.springboot.service;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import petermartesc.springboot.exception.ResourceNotFoundException;
import petermartesc.springboot.model.Location;
import petermartesc.springboot.repository.LocationRepository;
import petermartesc.springboot.service.interfaces.ILocationService;

import java.util.List;

@Component
public class LocationService implements ILocationService {

    private LocationRepository locationRepository;

    @Autowired
    public void setLocationRepository(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    @Override
    public Location getLocationById(@PathVariable(value = "id") int locationId) throws ResourceNotFoundException {
        return locationRepository.findById(locationId)
                .orElseThrow(()-> new ResourceNotFoundException("Location not found for this id :: " + locationId));
    }

    @Override
    public Location createLocation(@Valid @RequestBody Location location) {
        return locationRepository.save(location);
    }

    @Override
    public Location updateLocation(@PathVariable(value = "id") int locationId, @Valid @RequestBody Location locationDetails) throws ResourceNotFoundException {
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found for this id :: " + locationId));

        location.setName(locationDetails.getName());
        location.setKingdom(locationDetails.getKingdom());

        return locationRepository.save(location);
    }

    @Override
    public void deleteLocation(@PathVariable(value = "id") int locationId) throws ResourceNotFoundException {
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found for this id :: " + locationId));

        locationRepository.delete(location);
    }
}
