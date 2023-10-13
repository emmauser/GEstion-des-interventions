package org.institutsaintjean.gestionbancaire.repository;

import org.institutsaintjean.gestionbancaire.model.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
    Optional<Etudiant> findEtudiantByMatricule(String matricule);

    Etudiant findByMatricule(String matricule);


    Optional<Etudiant> findAllByCodeEtud(Long codeEtud);
}
