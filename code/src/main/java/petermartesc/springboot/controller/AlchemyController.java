package petermartesc.springboot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import petermartesc.springboot.exception.ResourceNotFoundException;
import petermartesc.springboot.model.Alchemy;
import petermartesc.springboot.service.interfaces.IAlchemyService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/alchemys")
public class AlchemyController {
    private IAlchemyService alchemyService;

    @Autowired
    public void setAlchemyRepository(IAlchemyService alchemyService) {
        this.alchemyService = alchemyService;
    }

    @Operation(summary = "Get all alchemys")
    @GetMapping("/")
    public List<Alchemy> getAllAlchemys() {
        return alchemyService.getAllAlchemys();
    }

    @Operation(summary = "Get alchemy by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Alchemy not found")
    })

    @GetMapping("/{id}")
    public ResponseEntity<Alchemy> getAlchemyById(@PathVariable(value = "id") int alchemyId) throws ResourceNotFoundException {
        Alchemy alchemy = alchemyService.getAlchemyById(alchemyId);
        return ResponseEntity.ok().body(alchemy);
    }

    @Operation(summary = "Insert alchemy")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alchemy created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/add/")
    public Alchemy createAlchemy(@Valid @RequestBody Alchemy alchemy) {
        return alchemyService.createAlchemy(alchemy);
    }

    @Operation(summary = "Update alchemy")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "alchemy updated successfully"),
            @ApiResponse(responseCode = "404", description = "alchemy not found")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<Alchemy> updateAlchemy(@PathVariable(value = "id") int alchemyId,
                                                 @Valid @RequestBody Alchemy alchemyDetails) throws ResourceNotFoundException {
        final Alchemy updateAlchemy = alchemyService.updateAlchemy(alchemyId, alchemyDetails);
        return ResponseEntity.ok(updateAlchemy);
    }

    @Operation(summary = "Delete alchemy")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alchemy deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Alchemy not found")
    })
    @DeleteMapping("/delete/{id}")
    public Map<String, Boolean> deleteAlchemy(@PathVariable(value = "id") int alchemyId) throws ResourceNotFoundException {
        alchemyService.deleteAlchemy(alchemyId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
