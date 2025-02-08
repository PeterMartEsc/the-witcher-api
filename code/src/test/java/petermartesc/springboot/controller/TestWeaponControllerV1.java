package petermartesc.springboot.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import petermartesc.springboot.exception.ResourceNotFoundException;
import petermartesc.springboot.model.Weapon;
import petermartesc.springboot.service.rest.WeaponService;
import petermartesc.springboot.utilities.Utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class TestWeaponControllerV1 extends Utilities {

    @Mock
    WeaponService mockWeaponService;

    @InjectMocks
    WeaponControllerV1 controller;

    @BeforeEach
    public void beforeEach (){
        MockitoAnnotations.openMocks(this);
        controller = new WeaponControllerV1();
        controller.setWeaponService(mockWeaponService);
    }

    @Test
    void getAllTest() {
        List<Weapon> list = new ArrayList<>();
        list.add(new Weapon(1, "Ejemplo Nombre","Ejemplo rarity"));
        list.add(new Weapon(1, "Ejemplo Nombre","Ejemplo rarity"));

        when(mockWeaponService.getAllWeapons()).thenReturn(list);
        Assertions.assertNotNull(controller.getAllWeapons(), NOT_EXPECTED_RESULT);
    }

    @Test
    void getOneTest() throws ResourceNotFoundException {

        when(mockWeaponService.getWeaponById(1)).thenReturn(new Weapon(1, "Ejemplo Nombre","Ejemplo rarity"));
        Assertions.assertNotNull(controller.getWeaponById(1), NOT_EXPECTED_RESULT);
    }

    @Test
    void addTest() {
        Weapon weapon = new Weapon(1, "Ejemplo Nombre","Ejemplo rarity");

        when(mockWeaponService.createWeapon(any(Weapon.class))).thenReturn(weapon);
        Weapon characterResponse = controller.createWeapon(weapon);
        Assertions.assertEquals(weapon, characterResponse, NOT_EXPECTED_RESULT);
    }

    @Test
    void deleteTest() throws ResourceNotFoundException {
        Map<String, Boolean> expectedResponse = new HashMap<>();
        expectedResponse.put("deleted", Boolean.TRUE);

        Map<String, Boolean> responseEntity = controller.deleteWeapon(1);
        Assertions.assertEquals(expectedResponse, responseEntity, NOT_EXPECTED_RESULT);
    }

    @Test
    void updateTest() throws ResourceNotFoundException {
        Weapon weapon = new Weapon(2, "Ejemplo Nombre","Ejemplo rarity");

        when(mockWeaponService.updateWeapon(2, weapon)).thenReturn(weapon);

        ResponseEntity responseEntity = controller.updateWeapon(2, weapon);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode(), NOT_EXPECTED_RESULT);
    }
}
