package petermartesc.springboot.service.interfaces;

import petermartesc.springboot.exception.ResourceNotFoundException;
import petermartesc.springboot.model.Weapon;

import java.util.List;

public interface IWeaponService {

    List<Weapon> getAllWeapons();
    Weapon getWeaponById(int weaponId) throws ResourceNotFoundException;
    public Weapon createWeapon(Weapon weapon);
    Weapon updateWeapon(int weaponId, Weapon weaponDetails) throws ResourceNotFoundException;
    void deleteWeapon(int weaponId) throws ResourceNotFoundException;
}
