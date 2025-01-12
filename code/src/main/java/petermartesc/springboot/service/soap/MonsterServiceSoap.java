package petermartesc.springboot.service.soap;

import jakarta.xml.ws.WebServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import petermartesc.springboot.exception.ResourceNotFoundException;
import petermartesc.springboot.model.Monster;
import petermartesc.springboot.service.rest.interfaces.IMonsterService;
import petermartesc.springboot.service.soap.interfaces.IMonsterServiceSoap;

import java.util.List;

public class MonsterServiceSoap implements IMonsterServiceSoap {

    private IMonsterService monsterService;

    @Autowired
    public void setMonsterRepository(IMonsterService monsterService) {
        this.monsterService = monsterService;
    }

    @Override
    public List<Monster> getAllMonsters() {
        return monsterService.getAllMonsters();
    }

    @Override
    public Monster getMonsterById(int monsterId) {
        try {
            return monsterService.getMonsterById(monsterId);
        } catch (ResourceNotFoundException e) {
            throw new WebServiceException("Error obteniendo el monstruo", e);
        }
    }

    @Override
    public boolean createMonster(Monster monster) {
        if(monster == null){
            return false;
        }
        monsterService.createMonster(monster);
        return true;
    }

    @Override
    public boolean updateMonster(Monster monster, int monsterId) {
        try {
            monsterService.updateMonster(monsterId, monster);

        } catch (ResourceNotFoundException e) {
            throw new WebServiceException("Error al actualizar el monstruo", e);
        }

        return true;
    }

    @Override
    public boolean deleteMonsterById(int monsterId) {
        try {
            monsterService.deleteMonster(monsterId);
            return true;
        } catch (ResourceNotFoundException e) {
            //Devuelve un error al obtener para no dar más información, no se especifica si existe o no
            throw new WebServiceException("Error al eliminar el monstruo", e);
        }
    }
}
