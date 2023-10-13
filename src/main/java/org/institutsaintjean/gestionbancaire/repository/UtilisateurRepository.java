package org.institutsaintjean.gestionbancaire.repository;

import org.institutsaintjean.gestionbancaire.model.Etudiant;
import org.institutsaintjean.gestionbancaire.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Optional<Utilisateur> findUtilisateurByLogin(String login);

    Optional<Utilisateur> findAllByCodeUtilisateur(Long code);
}
