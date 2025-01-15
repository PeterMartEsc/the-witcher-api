package petermartesc.springboot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import petermartesc.springboot.exception.ResourceNotFoundException;
import petermartesc.springboot.model.Monster;
import petermartesc.springboot.service.rest.interfaces.IMonsterService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/monsters")
public class MonsterController {

    private IMonsterService monsterService;

    @Autowired
    public void setMonsterRepository(IMonsterService monsterService) {
        this.monsterService = monsterService;
    }

    @Operation(summary = "Get all monsters")
    @GetMapping("/")
    public List<Monster> getAllMonsters() {
        return monsterService.getAllMonsters();
    }

    @Operation(summary = "Get monster by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Monster not found")
    })

    @GetMapping("/{id}")
    public ResponseEntity<Monster> getCHaracterById(@PathVariable(value = "id") int monsterId) throws ResourceNotFoundException {
        Monster monster = monsterService.getMonsterById(monsterId);
        return ResponseEntity.ok().body(monster);
    }

    @Operation(summary = "Insert monster")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Monster created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/add/")
    public Monster createMonster(@Valid @RequestBody Monster monster) {
        return monsterService.createMonster(monster);
    }

    @Operation(summary = "Update monster")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "monster updated successfully"),
            @ApiResponse(responseCode = "404", description = "monster not found")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<Monster> updateMonster(@PathVariable(value = "id") int monsterId,
                                                     @Valid @RequestBody Monster monsterDetails) throws ResourceNotFoundException {
        final Monster updateMonster = monsterService.updateMonster(monsterId, monsterDetails);
        return ResponseEntity.ok(updateMonster);
    }

    @Operation(summary = "Delete monster")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Monster deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Monster not found")
    })
    @DeleteMapping("/delete/{id}")
    public Map<String, Boolean> deleteMonster(@PathVariable(value = "id") int monsterId) throws ResourceNotFoundException {
        monsterService.deleteMonster(monsterId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
