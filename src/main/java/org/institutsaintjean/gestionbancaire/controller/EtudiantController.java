package org.institutsaintjean.gestionbancaire.controller;


import jakarta.mail.MessagingException;
import org.institutsaintjean.gestionbancaire.model.Etudiant;
import org.institutsaintjean.gestionbancaire.model.Intervention;
import org.institutsaintjean.gestionbancaire.model.Utilisateur;
import org.institutsaintjean.gestionbancaire.service.EmailService;
import org.institutsaintjean.gestionbancaire.service.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EtudiantController {


    @Autowired
    EtudiantService etudiantService;

    @Autowired
    EmailService emailService;

    @GetMapping("/listE")
    public String getEtudiants(Model model){
        Iterable<Etudiant> etudiants = etudiantService.getEtudiants();
        model.addAttribute("ListeEtudiant", etudiants);
        Etudiant etudiant = new Etudiant();
        model.addAttribute("etudiant", etudiant);
        Utilisateur utilisateur = new Utilisateur();
        model.addAttribute("utilisateur", utilisateur);
        Intervention intervention = new Intervention();
        model.addAttribute("intervention", intervention);
        return "listeEtud";
    }


    @PostMapping("/saveE")
    public String saveEtudiant(@ModelAttribute("etudiant") Etudiant etudiant) {
        etudiant.setRoles("Etudiant");
        etudiantService.saveEtudiant(etudiant);
        String infos = "Votre matricule est : " + etudiant.getMatricule() + " | Votre code d'authentification est : " + etudiant.getCode_authentification();
        emailService.sendEmail(etudiant.getMail_etud(), infos, "Informations de connexion application de gestion des interventions");
        System.out.println(etudiant.getMail_etud());

        return "redirect:/listE";
    }

    @GetMapping("/delE/{codeEtud}")
    public String deleteEtudiant(@PathVariable(value = "codeEtud") Long codeEtud){
        etudiantService.deleteEtudiant(codeEtud);
        return "redirect:/listE";

    }
}
