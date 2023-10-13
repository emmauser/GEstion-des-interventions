package org.institutsaintjean.gestionbancaire.repository;

import org.institutsaintjean.gestionbancaire.model.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DepartementRepository extends JpaRepository<Departement, Long> {

    Departement getDepartementByCodeDep(Long code);

}
