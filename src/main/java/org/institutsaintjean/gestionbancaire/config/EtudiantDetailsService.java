package org.institutsaintjean.gestionbancaire.config;

import org.institutsaintjean.gestionbancaire.model.Etudiant;
import org.institutsaintjean.gestionbancaire.repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class EtudiantDetailsService implements UserDetailsService {


    @Autowired
    private EtudiantRepository repository;

    @Override
    public UserDetails loadUserByUsername(String matricule) throws UsernameNotFoundException {
        Optional<Etudiant> etudiant = repository.findEtudiantByMatricule(matricule);
//        System.out.println(employe.map(EmployeDetails::new));
        return etudiant.map(EtudiantDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + matricule));

    }


}
