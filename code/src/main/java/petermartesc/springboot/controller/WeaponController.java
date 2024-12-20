package petermartesc.springboot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import petermartesc.springboot.exception.ResourceNotFoundException;
import petermartesc.springboot.model.Weapon;
import petermartesc.springboot.service.interfaces.IWeaponService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/weapons")
public class WeaponController {

    private IWeaponService weaponService;

    @Autowired
    public void setWeaponRepository(IWeaponService weaponService) {
        this.weaponService = weaponService;
    }

    @Operation(summary = "Get all weapons")
    @GetMapping("/")
    public List<Weapon> getAllWeapons() {
        return weaponService.getAllWeapons();
    }

    @Operation(summary = "Get weapon by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Weapon not found")
    })

    @GetMapping("/{id}")
    public ResponseEntity<Weapon> getCHaracterById(@PathVariable(value = "id") int weaponId) throws ResourceNotFoundException {
        Weapon weapon = weaponService.getWeaponById(weaponId);
        return ResponseEntity.ok().body(weapon);
    }

    @Operation(summary = "Insert weapon")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Weapon created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/add/")
    public Weapon createWeapon(@Valid @RequestBody Weapon weapon) {
        return weaponService.createWeapon(weapon);
    }

    @Operation(summary = "Update weapon")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "weapon updated successfully"),
            @ApiResponse(responseCode = "404", description = "weapon not found")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<Weapon> updateWeapon(@PathVariable(value = "id") int weaponId,
                                                 @Valid @RequestBody Weapon weaponDetails) throws ResourceNotFoundException {
        final Weapon updateWeapon = weaponService.updateWeapon(weaponId, weaponDetails);
        return ResponseEntity.ok(updateWeapon);
    }

    @Operation(summary = "Delete weapon")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Weapon deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Weapon not found")
    })
    @DeleteMapping("/delete/{id}")
    public Map<String, Boolean> deleteWeapon(@PathVariable(value = "id") int weaponId) throws ResourceNotFoundException {
        weaponService.deleteWeapon(weaponId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
