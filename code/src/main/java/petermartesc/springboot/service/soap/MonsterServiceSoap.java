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
    public Monster getMonsterById(int monsterId) throws ResourceNotFoundException {
        return monsterService.getMonsterById(monsterId);
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
    public boolean updateMonster(Monster monster, int monsterId) throws ResourceNotFoundException {
        monsterService.updateMonster(monsterId, monster);
        return true;
    }

    @Override
    public boolean deleteMonsterById(int monsterId) throws ResourceNotFoundException {
        monsterService.deleteMonster(monsterId);
        return true;
    }
}
