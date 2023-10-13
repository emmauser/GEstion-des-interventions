package org.institutsaintjean.gestionbancaire.service;

import org.institutsaintjean.gestionbancaire.model.Utilisateur;
import org.institutsaintjean.gestionbancaire.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UtilisateurService {

    @Autowired
    UtilisateurRepository utilisateurRepository;

    public List<Utilisateur> getUtilisateurs(){ return utilisateurRepository.findAll();}

    public void saveUtilisateur(Utilisateur utilisateur){ utilisateurRepository.save(utilisateur);}

    public void deleteUtilisateur(Long code){ utilisateurRepository.deleteById(code);}

    public Optional<Utilisateur> findUser(String login){ return utilisateurRepository.findUtilisateurByLogin(login);}
}
