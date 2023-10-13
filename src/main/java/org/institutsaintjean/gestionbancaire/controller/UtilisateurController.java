package org.institutsaintjean.gestionbancaire.controller;


import org.institutsaintjean.gestionbancaire.model.Etudiant;
import org.institutsaintjean.gestionbancaire.model.Intervention;
import org.institutsaintjean.gestionbancaire.model.Utilisateur;
import org.institutsaintjean.gestionbancaire.repository.DepartementRepository;
import org.institutsaintjean.gestionbancaire.service.EmailService;
import org.institutsaintjean.gestionbancaire.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

@Controller
public class UtilisateurController {

    @Autowired
    UtilisateurService utilisateurService;

    @Autowired
    DepartementRepository departementRepository;

    @Autowired
    EmailService emailService;

    @GetMapping("/listU")
    public String getUtilisateurs(Model model){
        Iterable<Utilisateur> utilisateurs = utilisateurService.getUtilisateurs();
        model.addAttribute("ListeUtilisateur", utilisateurs);
        Utilisateur utilisateur = new Utilisateur();
        model.addAttribute("utilisateur", utilisateur);
        Etudiant etudiant = new Etudiant();
        model.addAttribute("etudiant", etudiant);
        Intervention intervention = new Intervention();
        model.addAttribute("intervention", intervention);
        return "listePerso";
    }

    @PostMapping("/saveU")
    public String saveUtilisateur(@ModelAttribute("utilisateur") Utilisateur utilisateur){
        utilisateur.setDepartement(departementRepository.getDepartementByCodeDep(utilisateur.getDepartement().getCodeDep()));
        utilisateur.setStatut("ACTIVE");
        utilisateur.setStatut_vie("ACTIVE");
        utilisateur.setDate_naissance((Date) utilisateur.getDate_naissance());
        utilisateurService.saveUtilisateur(utilisateur);
        String infos = "Votre login est : " + utilisateur.getLogin() + " | Votre mot de passe est : " + utilisateur.getMot_de_passe();
        emailService.sendEmail(utilisateur.getMail_user(), infos, "Informations de connexion : Application de gestion des interventions");
        System.out.println(utilisateur.getMail_user());

        return "redirect:/listU";
    }

    @GetMapping("/delU/{codeUtilisateur}")
    public String deleteUser(@PathVariable(value = "codeUtilisateur") Long codeUtilisateur){
        utilisateurService.deleteUtilisateur(codeUtilisateur);
        return "redirect:/listU";

    }
}
