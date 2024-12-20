package petermartesc.springboot.service.interfaces;

import petermartesc.springboot.exception.ResourceNotFoundException;
import petermartesc.springboot.model.Alchemy;

import java.util.List;

public interface IAlchemyService {
    List<Alchemy> getAllAlchemys();
    Alchemy getAlchemyById(int alchemyId) throws ResourceNotFoundException;
    public Alchemy createAlchemy(Alchemy alchemy);
    Alchemy updateAlchemy(int alchemyId, Alchemy alchemyDetails) throws ResourceNotFoundException;
    void deleteAlchemy(int alchemyId) throws ResourceNotFoundException;
}
