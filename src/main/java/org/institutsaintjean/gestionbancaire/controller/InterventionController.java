package org.institutsaintjean.gestionbancaire.controller;


import jakarta.servlet.http.HttpServletRequest;
import org.institutsaintjean.gestionbancaire.config.EtudiantDetails;
import org.institutsaintjean.gestionbancaire.config.EtudiantDetailsService;
import org.institutsaintjean.gestionbancaire.model.Etudiant;
import org.institutsaintjean.gestionbancaire.model.Intervention;
import org.institutsaintjean.gestionbancaire.model.Utilisateur;
import org.institutsaintjean.gestionbancaire.repository.DepartementRepository;
import org.institutsaintjean.gestionbancaire.repository.EtudiantRepository;
import org.institutsaintjean.gestionbancaire.repository.UtilisateurRepository;
import org.institutsaintjean.gestionbancaire.service.EmailService;
import org.institutsaintjean.gestionbancaire.service.InterventionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.Optional;

@Controller
public class InterventionController {

    @Autowired
    InterventionService interventionService;

    @Autowired
    DepartementRepository departementRepository;

    @Autowired
    EtudiantRepository etudiantRepository;

    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Autowired
    EmailService emailService;


//    public String getEtudiantDetails(Authentication authentication) {
//        // Récupérer les informations de l'utilisateur authentifié
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//
//        // Accéder aux informations de l'utilisateur
//        String username = userDetails.getUsername();
//        // Autres informations disponibles : userDetails.getPassword(), userDetails.getAuthorities(), etc.
//
//        // Faites quelque chose avec les informations de l'étudiant
//        // Par exemple, retournez les informations dans la réponse du contrôleur
//        return "Informations de l'étudiant connecté : " + username;
//    }



    @GetMapping("/listI")
    public String getInterventions(Model model){
        Iterable<Intervention> interventions = interventionService.getInterventions();
        model.addAttribute("ListeIntervention", interventions);

        Intervention intervention = new Intervention();
        model.addAttribute("intervention", intervention);

        Etudiant etudiant = new Etudiant();
        model.addAttribute("etudiant", etudiant);

        Utilisateur utilisateur = new Utilisateur();
        model.addAttribute("utilisateur", utilisateur);
        return "listeInt";
    }

    @PostMapping("/saveI")
    public String saveIntervention(@ModelAttribute("intervention") Intervention intervention){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getName());
        intervention.setDepartement(departementRepository.getDepartementByCodeDep(intervention.getDepartement().getCodeDep()));
        intervention.setEtudiant(etudiantRepository.findByMatricule(auth.getName()));
        intervention.setStatutInt("Publié");
        interventionService.saveIntervention(intervention);
        return "redirect:/accueil";
    }

    @GetMapping("/delI/{codeInt}")
    public String deleteIntervention(@PathVariable(value = "codeInt") Long codeInt){
        interventionService.deleteIntervention(codeInt);
        return "redirect:/listI";

    }

    @GetMapping("/resolveI/{codeInt}")
    public String resoudreIntervention(@PathVariable(value = "codeInt") Long codeInt, @ModelAttribute("statutInt") Intervention intervention){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String infos = "Votre requête a bien été prise en charge par l'utilisateur : " + auth.getName();
        Intervention intervention1 = interventionService.getIntervention(codeInt);
        System.out.println(intervention1);
        String destinataire = intervention1.getEtudiant().getMail_etud();
        emailService.sendEmail(destinataire, infos, "Intervention prise en charge");
        intervention1.setStatutInt("En cours");
        interventionService.saveIntervention(intervention1);
        return "redirect:/listI";
    }

    @PostMapping("/selecttest")
    public String resoudreIntervention(HttpServletRequest request){
        String selectedOption = request.getParameter("selectedOption");

        // Faire quelque chose avec la valeur sélectionnée
        System.out.println("Option sélectionnée : " + request.getAttribute("statutInt"));

        // Ajouter la logique de traitement ici

        // Rediriger vers une page ou renvoyer une réponse appropriée

        return "redirect:/listI";
    }

}
