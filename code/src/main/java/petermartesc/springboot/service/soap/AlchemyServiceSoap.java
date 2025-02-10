package petermartesc.springboot.service.soap;

import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import petermartesc.springboot.exception.ResourceNotFoundException;
import petermartesc.springboot.model.Alchemy;
import petermartesc.springboot.service.rest.AlchemyService;
import petermartesc.springboot.service.rest.interfaces.IAlchemyService;
import petermartesc.springboot.service.soap.interfaces.IAlchemyServiceSoap;

import java.util.List;


public class AlchemyServiceSoap implements IAlchemyServiceSoap {

    private IAlchemyService alchemyService;

    @Autowired
    public void setAlchemyRepository(AlchemyService alchemyService) {
        this.alchemyService = alchemyService;
    }

    @Override
    public List<Alchemy> getAllAlchemys() {
        return alchemyService.getAllAlchemys();
    }

    @Override
    public Alchemy getAlchemyById(int alchemyId) throws ResourceNotFoundException {
        return alchemyService.getAlchemyById(alchemyId);
    }

    @Override
    public boolean createAlchemy(Alchemy alchemy) {
        if(alchemy == null){
            return false;
        }
        alchemyService.createAlchemy(alchemy);
        return true;
    }

    @Override
    public boolean updateAlchemy(Alchemy alchemy, int alchemyId) {
        try {
            alchemyService.updateAlchemy( alchemyId, alchemy);
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    @Override
    public boolean deleteAlchemyById(int alchemyId) {
        try {
            alchemyService.deleteAlchemy(alchemyId);
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
