package org.institutsaintjean.gestionbancaire.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Departement {

    @Id
    private Long codeDep;
    private Date date_creation;
    private Date date_modification;
    private String description;
    private String libelle;
    private String signature;
    private String statut_vie;
    private Long createur;
    private Long modificateur;


    @ManyToMany
    @JoinTable( name = "utilisateur_departement",
            joinColumns = @JoinColumn( name = "codeDep" ),
            inverseJoinColumns = @JoinColumn( name = "codeUtilisateur" ) )
    private List<Utilisateur> utilisateurs = new ArrayList<>();

}
