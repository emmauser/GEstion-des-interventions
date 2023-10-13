package org.institutsaintjean.gestionbancaire.service;

import org.institutsaintjean.gestionbancaire.model.Etudiant;
import org.institutsaintjean.gestionbancaire.repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EtudiantService {

    @Autowired
    EtudiantRepository etudiantRepository;

    public List<Etudiant> getEtudiants(){ return etudiantRepository.findAll();}

    public void saveEtudiant(Etudiant etudiant){ etudiantRepository.save(etudiant);}

    public void deleteEtudiant(Long code){ etudiantRepository.deleteById(code);}
}
