package petermartesc.springboot.service.soap;

import jakarta.xml.ws.WebServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import petermartesc.springboot.exception.ResourceNotFoundException;
import petermartesc.springboot.model.Alchemy;
import petermartesc.springboot.service.rest.interfaces.IAlchemyService;
import petermartesc.springboot.service.soap.interfaces.IAlchemyServiceSoap;

import java.util.List;

public class AlchemyServiceSoap implements IAlchemyServiceSoap {

    private IAlchemyService alchemyService;

    @Autowired
    public void setAlchemyRepository(IAlchemyService alchemyService) {
        this.alchemyService = alchemyService;
    }

    @Override
    public List<Alchemy> getAllAlchemys() {
        return alchemyService.getAllAlchemys();
    }

    @Override
    public Alchemy getAlchemyById(int alchemyId) {
        try {
            return alchemyService.getAlchemyById(alchemyId);
        } catch (ResourceNotFoundException e) {
            //Devuelve un error al obtener para no dar más información, no se especifica si existe o no
            throw new WebServiceException("Error obteniendo la alquimia", e);
        }
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
            alchemyService.updateAlchemy(alchemyId, alchemy);
        } catch (ResourceNotFoundException e) {
            throw new WebServiceException("Error al actualizar la alquimia", e);
        }

        return true;
    }

    @Override
    public boolean deleteAlchemyById(int alchemyId) {
        try {
            alchemyService.deleteAlchemy(alchemyId);
            return true;
        } catch (ResourceNotFoundException e) {
            //Devuelve un error al obtener para no dar más información, no se especifica si existe o no
            throw new WebServiceException("Error eliminar la alquimia", e);
        }
    }
}
