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
    public Location getLocationById(int locationId) {
        try {
            return locationService.getLocationById(locationId);
        } catch (ResourceNotFoundException e) {
            throw new WebServiceException("Error obteniendo la localizacion", e);
        }
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
    public boolean updateLocation(Location location, int locationId) {
        try {
            /**Location locationToUpdate = locationService.getLocationById(locationId);
             locationToUpdate.setLocationName(location.getLocationName());**/
            locationService.updateLocation(locationId, location);

        } catch (ResourceNotFoundException e) {
            throw new WebServiceException("Error al actualizar la localizacion", e);
        }

        return true;
    }

    @Override
    public boolean deleteLocationById(int locationId) {
        try {
            locationService.deleteLocation(locationId);
            return true;
        } catch (ResourceNotFoundException e) {
            //Devuelve un error al obtener para no dar más información, no se especifica si existe o no
            throw new WebServiceException("Error al eliminar la localizacion", e);
        }
    }
}
