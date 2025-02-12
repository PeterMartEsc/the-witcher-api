package petermartesc.springboot.service.soap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import petermartesc.springboot.exception.ResourceNotFoundException;
import petermartesc.springboot.model.Weapon;
import petermartesc.springboot.service.rest.WeaponService;
import petermartesc.springboot.utilities.Utilities;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class TestWeaponSoapService extends Utilities {

    @Mock
    WeaponService repositoryWeaponMock;

    @InjectMocks
    WeaponServiceSoap weaponSoapService;


    @BeforeEach
    public void beforeEach (){
        MockitoAnnotations.openMocks(this);
        weaponSoapService = new WeaponServiceSoap();
        weaponSoapService.setWeaponRepository(repositoryWeaponMock);
    }
    @Test
    void getAllTest() {
        List<Weapon> list = new ArrayList<>();
        list.add(WEAPON);
        list.add(WEAPON);
        when(repositoryWeaponMock.getAllWeapons()).thenReturn(list);
        Assertions.assertNotNull(weaponSoapService.getAllWeapons(), NOT_EXPECTED_RESULT);
    }


    @Test
    void getOneTest() throws ResourceNotFoundException {
        when(repositoryWeaponMock.getWeaponById(1)).thenReturn(WEAPON);
        Assertions.assertNotNull(weaponSoapService.getWeaponById(1), NOT_EXPECTED_RESULT);
    }



    @Test
    void updateTest() throws ResourceNotFoundException {
        //User user = new User(NAME, ROLE);
        when(repositoryWeaponMock.getWeaponById(1)).thenReturn(WEAPON);
        when(repositoryWeaponMock.updateWeapon(ID, WEAPON)).thenReturn(WEAPON);
        //System.out.println(USER);
        Assertions.assertTrue( weaponSoapService.updateWeapon( WEAPON, 1), NOT_EXPECTED_RESULT);
    }

    @Test
    void zaddTest() throws ResourceNotFoundException {
        when(repositoryWeaponMock.createWeapon(any(Weapon.class))).thenReturn(WEAPON);
        Assertions.assertTrue(weaponSoapService.createWeapon(WEAPON), NOT_EXPECTED_RESULT);
    }

    @Test
    void deleteTest() throws ResourceNotFoundException {
        Weapon weapon = new Weapon(1, NAME, RARITY);
        when(repositoryWeaponMock.getWeaponById(1)).thenReturn(weapon);
        doNothing().when(repositoryWeaponMock).deleteWeapon(isA(Integer.class));
        weaponSoapService.deleteWeaponById(1);
        verify(repositoryWeaponMock, times(1)).deleteWeapon(1);
    }
}
