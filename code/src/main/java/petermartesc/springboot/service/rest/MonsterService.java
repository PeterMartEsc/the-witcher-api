package petermartesc.springboot.service.rest;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import petermartesc.springboot.exception.ResourceNotFoundException;
import petermartesc.springboot.model.Monster;
import petermartesc.springboot.repository.MonsterRepository;
import petermartesc.springboot.service.rest.interfaces.IMonsterService;

import java.util.List;

@Component
public class MonsterService implements IMonsterService {

    private MonsterRepository monsterRepository;

    @Autowired
    public void setMonsterRepository(MonsterRepository monsterRepository) {
        this.monsterRepository = monsterRepository;
    }

    @Override
    public List<Monster> getAllMonsters() {
        return monsterRepository.findAll();
    }

    @Override
    public Monster getMonsterById(@PathVariable(value = "id") int monsterId) throws ResourceNotFoundException {
        return monsterRepository.findById(monsterId)
                .orElseThrow(()-> new ResourceNotFoundException("Monster not found for this id :: " + monsterId));
    }

    @Override
    public Monster createMonster(@Valid @RequestBody Monster monster) {
        return monsterRepository.save(monster);
    }

    @Override
    public Monster updateMonster(@PathVariable(value = "id") int monsterId, @Valid @RequestBody Monster monsterDetails) throws ResourceNotFoundException {
        Monster monster = monsterRepository.findById(monsterId)
                .orElseThrow(() -> new ResourceNotFoundException("Monster not found for this id :: " + monsterId));

        monster.setName(monsterDetails.getName());
        monster.setDifficulty(monsterDetails.getDifficulty());

        return monsterRepository.save(monster);
    }

    @Override
    public void deleteMonster(@PathVariable(value = "id") int monsterId) throws ResourceNotFoundException {
        Monster monster = monsterRepository.findById(monsterId)
                .orElseThrow(() -> new ResourceNotFoundException("Monster not found for this id :: " + monsterId));

        monsterRepository.delete(monster);
    }
}
