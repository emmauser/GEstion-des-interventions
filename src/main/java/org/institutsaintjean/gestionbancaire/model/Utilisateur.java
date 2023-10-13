package org.institutsaintjean.gestionbancaire.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codeUtilisateur;
    private Date date_creation;
    private Date date_modification;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date_naissance;

    private String mail_user;

    @Column(unique = true)
    private String login;

    private String mot_de_passe;
    private String nom;
    private String prenom;
    private String sexe;
    private String roles;
    private String signature;
    private String statut;
    private String statut_vie;
    private String telephone;

    @ManyToOne
    @JoinColumn(name = "codeDep")
    private Departement departement;

    @ManyToMany
    @JoinTable( name = "utilisateur_departement",
            joinColumns = @JoinColumn( name = "codeUtilisateur" ),
            inverseJoinColumns = @JoinColumn( name = "codeDep" ) )
    private List<Departement> departements = new ArrayList<>();


}
