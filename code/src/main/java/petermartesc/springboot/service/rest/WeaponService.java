package petermartesc.springboot.service.rest;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import petermartesc.springboot.exception.ResourceNotFoundException;
import petermartesc.springboot.model.Weapon;
import petermartesc.springboot.repository.WeaponRepository;
import petermartesc.springboot.service.rest.interfaces.IWeaponService;

import java.util.List;

@Component
public class WeaponService implements IWeaponService {
    private WeaponRepository weaponRepository;

    @Autowired
    public void setWeaponRepository(WeaponRepository weaponRepository) {
        this.weaponRepository = weaponRepository;
    }

    @Override
    public List<Weapon> getAllWeapons() {
        return weaponRepository.findAll();
    }

    @Override
    public Weapon getWeaponById(@PathVariable(value = "id") int weaponId) throws ResourceNotFoundException {
        return weaponRepository.findById(weaponId)
                .orElseThrow(()-> new ResourceNotFoundException("Weapon not found for this id :: " + weaponId));
    }

    @Override
    public Weapon createWeapon(@Valid @RequestBody Weapon weapon) {
        return weaponRepository.save(weapon);
    }

    @Override
    public Weapon updateWeapon(@PathVariable(value = "id") int weaponId, @Valid @RequestBody Weapon weaponDetails) throws ResourceNotFoundException {
        Weapon weapon = weaponRepository.findById(weaponId)
                .orElseThrow(() -> new ResourceNotFoundException("Weapon not found for this id :: " + weaponId));

        weapon.setName(weaponDetails.getName());
        weapon.setRarity(weaponDetails.getRarity());

        return weaponRepository.save(weapon);
    }

    @Override
    public void deleteWeapon(@PathVariable(value = "id") int weaponId) throws ResourceNotFoundException {
        Weapon weapon = weaponRepository.findById(weaponId)
                .orElseThrow(() -> new ResourceNotFoundException("Weapon not found for this id :: " + weaponId));

        weaponRepository.delete(weapon);
    }
}
