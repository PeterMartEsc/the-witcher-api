package petermartesc.springboot.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import petermartesc.springboot.exception.ResourceNotFoundException;
import petermartesc.springboot.model.Weapon;
import petermartesc.springboot.repository.WeaponRepository;
import petermartesc.springboot.service.rest.WeaponService;
import petermartesc.springboot.utilities.Utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class TestWeaponService extends Utilities {

    @Mock
    WeaponRepository repositoryWeaponMock;

    @InjectMocks
    WeaponService weaponService;

    /*@InjectMocks
    WeaponService weaponService;*/

    @BeforeEach
    public void beforeEach (){
        MockitoAnnotations.openMocks(this);
        weaponService = new WeaponService();
        weaponService.setWeaponRepository(repositoryWeaponMock);
    }
    @Test
    void getAllTest() {
        List<Weapon> list = new ArrayList<>();
        list.add(new Weapon(3, NAME, RARITY));
        list.add(new Weapon(4, NAME, RARITY));
        when(repositoryWeaponMock.findAll()).thenReturn(list);
        Assertions.assertNotNull(weaponService.getAllWeapons(), NOT_EXPECTED_RESULT);
    }


    @Test
    void getOneTest() throws ResourceNotFoundException {
        Weapon weapon = new Weapon(1, NAME, RARITY);
        when(repositoryWeaponMock.findById(1)).thenReturn(Optional.of(weapon));
        Assertions.assertNotNull(weaponService.getWeaponById(1), NOT_EXPECTED_RESULT);
    }



    @Test
    void updateTest() throws ResourceNotFoundException {
        Weapon weapon = new Weapon(1, NAME, RARITY);
        when(repositoryWeaponMock.findById(1)).thenReturn(Optional.of(weapon));
        when(repositoryWeaponMock.save(any(Weapon.class))).thenReturn(weapon);
        //System.out.println(USER);
        Assertions.assertEquals(weapon, weaponService.updateWeapon(ID, weapon), NOT_EXPECTED_RESULT);
    }

    @Test
    void zaddTest() throws ResourceNotFoundException {
        Weapon weapon = new Weapon(1, NAME, RARITY);
        when(repositoryWeaponMock.save(any(Weapon.class))).thenReturn(weapon);
        Assertions.assertEquals(weapon, weaponService.createWeapon(weapon), NOT_EXPECTED_RESULT);
    }

    @Test
    void deleteTest() throws ResourceNotFoundException {
        Weapon weapon = new Weapon(1, NAME, RARITY);
        when(repositoryWeaponMock.findById(1)).thenReturn(Optional.of(weapon));
        doNothing().when(repositoryWeaponMock).delete(isA(Weapon.class));
        weaponService.deleteWeapon(1);
        verify(repositoryWeaponMock, times(1)).delete(weapon);

    }
}
