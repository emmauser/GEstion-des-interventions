package org.institutsaintjean.gestionbancaire.repository;


import org.institutsaintjean.gestionbancaire.model.Intervention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterventionRepository extends JpaRepository<Intervention, Long> {

    @Query("SELECT i.departement.codeDep, COUNT(*) AS nbint FROM Intervention i GROUP BY i.departement.codeDep")
    List<Intervention> nbintbydep();

}
