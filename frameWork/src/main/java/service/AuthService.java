
package service;

import model.Utilisateur;
import model.UtilisateurRole;
import model.Role;

import repository.UtilisateurRepository;
import repository.UtilisateurRoleRepository;
import repository.RoleRepository;

import mg.etu4370.annotation.Inject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthService {

    @Inject
    private final UtilisateurRepository utilisateurRepository;

    @Inject
    private final UtilisateurRoleRepository utilisateurRoleRepository;

    @Inject
    private final RoleRepository roleRepository;

    public AuthService(
            UtilisateurRepository utilisateurRepository,
            UtilisateurRoleRepository utilisateurRoleRepository,
            RoleRepository roleRepository) {

        this.utilisateurRepository = utilisateurRepository;
        this.utilisateurRoleRepository = utilisateurRoleRepository;
        this.roleRepository = roleRepository;
    }

    public Utilisateur login(String email, String mdp) {

        if (email == null || mdp == null) {
            return null;
        }

        email = email.trim();

        if (email.isEmpty() || mdp.isEmpty()) {
            return null;
        }

        Utilisateur utilisateur = utilisateurRepository.findByEmail(email);

        if (utilisateur == null) {
            return null;
        }

        if (!mdp.equals(utilisateur.getMdp())) {
            return null;
        }

        return utilisateur;
    }

    public Role getRole(Integer idUser) {


        if (idUser == null) {
            return null;
        }

        UtilisateurRole relation =
                utilisateurRoleRepository.findByIdUser(idUser);


        Role role = roleRepository.findById(relation.getIdRole()).orElse(null);
        return  role;

    }
    

    public Utilisateur findByEmail(String email) {
        if (email == null) return null;

        return utilisateurRepository.findByEmail(email.trim());
    }
}
