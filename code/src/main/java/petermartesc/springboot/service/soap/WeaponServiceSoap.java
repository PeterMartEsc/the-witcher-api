package petermartesc.springboot.service.soap;

import jakarta.xml.ws.WebServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import petermartesc.springboot.exception.ResourceNotFoundException;
import petermartesc.springboot.model.Weapon;
import petermartesc.springboot.service.rest.interfaces.IWeaponService;
import petermartesc.springboot.service.soap.interfaces.IWeaponServiceSoap;

import java.util.List;

public class WeaponServiceSoap implements IWeaponServiceSoap {

    private IWeaponService weaponService;

    @Autowired
    public void setWeaponRepository(IWeaponService weaponService) {
        this.weaponService = weaponService;
    }

    @Override
    public List<Weapon> getAllWeapons() {
        return weaponService.getAllWeapons();
    }

    @Override
    public Weapon getWeaponById(int weaponId) throws ResourceNotFoundException {
        return weaponService.getWeaponById(weaponId);
    }

    @Override
    public boolean createWeapon(Weapon weapon) {
        if(weapon == null){
            return false;
        }
        weaponService.createWeapon(weapon);
        return true;
    }

    @Override
    public boolean updateWeapon(Weapon weapon, int weaponId) throws ResourceNotFoundException {
        weaponService.updateWeapon(weaponId, weapon);
        return true;
    }

    @Override
    public boolean deleteWeaponById(int weaponId) throws ResourceNotFoundException {
        weaponService.deleteWeapon(weaponId);
        return true;
    }
}
