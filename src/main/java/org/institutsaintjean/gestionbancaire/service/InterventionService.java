package org.institutsaintjean.gestionbancaire.service;

import org.institutsaintjean.gestionbancaire.model.Intervention;
import org.institutsaintjean.gestionbancaire.model.Utilisateur;
import org.institutsaintjean.gestionbancaire.repository.InterventionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterventionService {

    @Autowired
    InterventionRepository interventionRepository;

    public  Intervention getIntervention(Long codeInt){ return interventionRepository.getReferenceById(codeInt);}

    //public Intervention getIntervention(String matricule)

    public List<Intervention> getInterventions(){ return interventionRepository.findAll();}

    public void saveIntervention(Intervention intervention){ interventionRepository.save(intervention);}

    public void deleteIntervention(Long code){ interventionRepository.deleteById(code);}

}
