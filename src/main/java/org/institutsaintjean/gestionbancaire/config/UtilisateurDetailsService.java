package org.institutsaintjean.gestionbancaire.config;

import org.institutsaintjean.gestionbancaire.model.Etudiant;
import org.institutsaintjean.gestionbancaire.model.Utilisateur;
import org.institutsaintjean.gestionbancaire.repository.EtudiantRepository;
import org.institutsaintjean.gestionbancaire.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class UtilisateurDetailsService implements UserDetailsService {


    @Autowired
    private UtilisateurRepository repository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<Utilisateur> utilisateur = repository.findUtilisateurByLogin(login);
//        System.out.println(employe.map(EmployeDetails::new));
        return utilisateur.map(UtilisateurDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + login));

    }


}
