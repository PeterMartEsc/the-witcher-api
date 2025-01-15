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
    public Weapon getWeaponById(int weaponId) {
        try {
            return weaponService.getWeaponById(weaponId);
        } catch (ResourceNotFoundException e) {
            throw new WebServiceException("Error obteniendo el arma", e);
        }
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
    public boolean updateWeapon(Weapon weapon, int weaponId) {
        try {
            weaponService.updateWeapon(weaponId, weapon);

        } catch (ResourceNotFoundException e) {
            throw new WebServiceException("Error al actualizar el arma", e);
        }

        return true;
    }

    @Override
    public boolean deleteWeaponById(int weaponId) {
        try {
            weaponService.deleteWeapon(weaponId);
            return true;
        } catch (ResourceNotFoundException e) {
            //Devuelve un error al obtener para no dar más información, no se especifica si existe o no
            throw new WebServiceException("Error al eliminar el arma", e);
        }
    }
}
