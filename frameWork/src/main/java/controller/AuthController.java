
package controller;

import model.Utilisateur;
import model.Role;

import service.AuthService;

import mg.etu4370.annotation.Controller;
import mg.etu4370.annotation.Inject;
import mg.etu4370.annotation.AnjaParameter;
import mg.etu4370.annotation.UrlMapping;

import mg.etu4370.utils.ModelAndView;
import mg.etu4370.utils.AnjaSession;


import java.util.List;

@Controller
public class AuthController {

    @Inject
    private AuthService authService;

    @UrlMapping(value = "/", method = "GET")
    public ModelAndView loginPage() {

        return new ModelAndView("views/auth/login");
    }

    @UrlMapping(value = "/login", method = "POST")
    public ModelAndView authentifier(
            @AnjaParameter(name = "email") String email,
            @AnjaParameter(name = "mdp") String mdp,
            AnjaSession session) {

        Utilisateur utilisateur = authService.login(email, mdp);

        if (utilisateur == null) {

            ModelAndView mv = new ModelAndView("views/auth/login");

            mv.addAttribut("erreur",
                    "Email ou mot de passe incorrect.");

            mv.addAttribut("email", email);

            return mv;
        }

        Role role = authService.getRole(utilisateur.getId());

        session.set("login", utilisateur);
        session.set("role", role.getLibelle());

         return new ModelAndView("redirect:/patients");

    }

    @UrlMapping(value = "/logout", method = "GET")
    public ModelAndView logout(AnjaSession session) {
        session.clear();        

        return new ModelAndView("views/auth/login");
    }
}
