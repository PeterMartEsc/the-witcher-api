package petermartesc.springboot.service.rest;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import petermartesc.springboot.exception.ResourceNotFoundException;
import petermartesc.springboot.model.Alchemy;
import petermartesc.springboot.repository.AlchemyRepository;
import petermartesc.springboot.service.rest.interfaces.IAlchemyService;

import java.util.List;

@Component
public class AlchemyService implements IAlchemyService {

    private AlchemyRepository alchemyRepository;

    @Autowired
    public void setAlchemyRepository(AlchemyRepository alchemyRepository) {
        this.alchemyRepository = alchemyRepository;
    }

    @Override
    public List<Alchemy> getAllAlchemys() {
        return alchemyRepository.findAll();
    }

    @Override
    public Alchemy getAlchemyById(@PathVariable(value = "id") int alchemyId) throws ResourceNotFoundException {
        return alchemyRepository.findById(alchemyId)
                .orElseThrow(()-> new ResourceNotFoundException("Alchemy not found for this id :: " + alchemyId));
    }

    @Override
    public Alchemy createAlchemy(@Valid @RequestBody Alchemy alchemy) {
        return alchemyRepository.save(alchemy);
    }

    @Override
    public Alchemy updateAlchemy(@PathVariable(value = "id") int alchemyId, @Valid @RequestBody Alchemy alchemyDetails) throws ResourceNotFoundException {
        Alchemy alchemy = alchemyRepository.findById(alchemyId)
                .orElseThrow(() -> new ResourceNotFoundException("Alchemy not found for this id :: " + alchemyId));

        alchemy.setName(alchemyDetails.getName());
        alchemy.setMaterial(alchemyDetails.getMaterial());

        return alchemyRepository.save(alchemy);
    }

    @Override
    public void deleteAlchemy(@PathVariable(value = "id") int alchemyId) throws ResourceNotFoundException {
        Alchemy alchemy = alchemyRepository.findById(alchemyId)
                .orElseThrow(() -> new ResourceNotFoundException("Alchemy not found for this id :: " + alchemyId));

        alchemyRepository.delete(alchemy);
    }
}
