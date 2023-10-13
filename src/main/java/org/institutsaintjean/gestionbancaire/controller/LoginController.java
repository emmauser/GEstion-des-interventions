package org.institutsaintjean.gestionbancaire.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.institutsaintjean.gestionbancaire.model.Etudiant;
import org.institutsaintjean.gestionbancaire.model.Intervention;
import org.institutsaintjean.gestionbancaire.model.Utilisateur;
import org.institutsaintjean.gestionbancaire.repository.EtudiantRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    private EtudiantRepository etudiantRepository;


    @RequestMapping("/")
    public String starterPage(){
        return "startingpage";
    }


    @RequestMapping("/loginetud")
    public String loginEtud() {
        return "loginetud";
    }

    @RequestMapping("/loginuser")
    public String loginUser() {
        return "loginuser";
    }


    @RequestMapping("/login-error1")
    public String loginErrorEtud(Model model, Etudiant etudiant) {
//        if(!employe.equals()){
//            model.addAttribute("notActive", true);
//        }
        model.addAttribute("loginError", true);
        return "loginetud";
    }

    @RequestMapping("/login-error2")
    public String loginErrorUser(Model model, Utilisateur utilisateur) {
//        if(!employe.equals()){
//            model.addAttribute("notActive", true);
//        }
        model.addAttribute("loginError", true);
        return "loginuser";
    }


    @RequestMapping("/accueil")
    public String login(Model model){
        Etudiant etudiant = new Etudiant();
        model.addAttribute("etudiant", etudiant);
        Utilisateur utilisateur = new Utilisateur();
        model.addAttribute("utilisateur", utilisateur);
        Intervention intervention = new Intervention();
        model.addAttribute("intervention", intervention);
        return "homepage";
    }


    @RequestMapping("/access-denied")
    public String accesrefuse(){
        return "accesrefuse";
    }

    @RequestMapping("/erreur404")
    public String errornotfound(HttpServletRequest request, ch.qos.logback.core.model.Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        System.out.println(status);
        return "error404";
    }
}
