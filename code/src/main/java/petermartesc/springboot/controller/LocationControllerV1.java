package petermartesc.springboot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import petermartesc.springboot.exception.ResourceNotFoundException;
import petermartesc.springboot.model.Location;
import petermartesc.springboot.service.rest.interfaces.ILocationService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/locations")
public class LocationControllerV1 {

    private ILocationService locationService;

    @Autowired
    public void setLocationService(ILocationService locationService) {
        this.locationService = locationService;
    }

    @Operation(summary = "Get all locations")
    @GetMapping("/")
    public List<Location> getAllLocations() {
        return locationService.getAllLocations();
    }

    @Operation(summary = "Get location by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Location not found")
    })

    @GetMapping("/{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable(value = "id") int locationId) throws ResourceNotFoundException {
        Location location = locationService.getLocationById(locationId);
        return ResponseEntity.ok().body(location);
    }

    @Operation(summary = "Insert location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Location created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/add/")
    public Location createLocation(@Valid @RequestBody Location location) {
        return locationService.createLocation(location);
    }

    @Operation(summary = "Update location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Location updated successfully"),
            @ApiResponse(responseCode = "404", description = "Location not found")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<Location> updateLocation(@PathVariable(value = "id") int locationId,
                                                     @Valid @RequestBody Location locationDetails) throws ResourceNotFoundException {
        final Location updateLocation = locationService.updateLocation(locationId, locationDetails);
        return ResponseEntity.ok(updateLocation);
    }

    @Operation(summary = "Delete location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Location deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Location not found")
    })
    @DeleteMapping("/delete/{id}")
    public Map<String, Boolean> deleteLocation(@PathVariable(value = "id") int locationId) throws ResourceNotFoundException {
        locationService.deleteLocation(locationId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
