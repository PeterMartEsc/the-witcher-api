package petermartesc.springboot.service.soap;

import jakarta.xml.ws.WebServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import petermartesc.springboot.exception.ResourceNotFoundException;
import petermartesc.springboot.model.Alchemy;
import petermartesc.springboot.service.rest.interfaces.IAlchemyService;
import petermartesc.springboot.service.soap.interfaces.IAlchemyServiceSoap;

import java.util.List;

public class AlchemyServiceSoap implements IAlchemyServiceSoap {

    private IAlchemyServiceSoap alchemyService;

    @Autowired
    public void setAlchemyRepository(IAlchemyServiceSoap alchemyService) {
        this.alchemyService = alchemyService;
    }

    @Override
    public List<Alchemy> getAllAlchemys() {
        return alchemyService.getAllAlchemys();
    }

    @Override
    public Alchemy getAlchemyById(int alchemyId) {
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
        alchemyService.updateAlchemy( alchemy, alchemyId);

        return true;
    }

    @Override
    public boolean deleteAlchemyById(int alchemyId) {
        alchemyService.deleteAlchemyById(alchemyId);
        return true;
    }
}
