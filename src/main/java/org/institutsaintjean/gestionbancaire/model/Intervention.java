package org.institutsaintjean.gestionbancaire.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Intervention {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codeInt;
    private String intitule;
    private String details;
    private String statutInt;
    private File piecejointe;

    @ManyToOne
    @JoinColumn(name = "codeEtud")
    private Etudiant etudiant;

    @ManyToOne
    @JoinColumn(name = "codeDep")
    private Departement departement;

}
