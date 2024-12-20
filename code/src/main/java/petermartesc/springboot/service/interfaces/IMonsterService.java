package petermartesc.springboot.service.interfaces;

import petermartesc.springboot.exception.ResourceNotFoundException;
import petermartesc.springboot.model.Monster;

import java.util.List;

public interface IMonsterService {
    List<Monster> getAllMonsters();
    Monster getMonsterById(int monsterId) throws ResourceNotFoundException;
    public Monster createMonster(Monster monster);
    Monster updateMonster(int monsterId, Monster monsterDetails) throws ResourceNotFoundException;
    void deleteMonster(int monsterId) throws ResourceNotFoundException;
}
