package com.diaraba.projetDeSoutenance.controllers;

import com.diaraba.projetDeSoutenance.models.*;
import com.diaraba.projetDeSoutenance.payload.response.MessageResponse;
import com.diaraba.projetDeSoutenance.payload.response.SignupResponse1;
import com.diaraba.projetDeSoutenance.payload.response.StructureResponse;
import com.diaraba.projetDeSoutenance.repository.*;
import com.diaraba.projetDeSoutenance.security.services.UtilisateurService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/utilisateur")
public class UtlisateurController {
    @Autowired
    UtilisateurService utilisateurService;
    @Autowired
    UtilisateurRepository utilisateurRepository;
    @Autowired
    ActivitesRepository activitesRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    StructureRepository structureRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private ProfileUtilisateurRepository profileUtilisateurRepository;
    @Autowired
    private ProfileStructureRepository profileStructureRepository;
    @Autowired
    private AbonnementRepository abonnementRepository;
    @Autowired
    private DemandeRepository demandeRepository;


    @GetMapping("afficherUtilisateur/{nomutilisateur}")
    public Utilisateurs afficherutilisateur(@PathVariable("nomutilisateur") String nomutilisateur) {

        return utilisateurService.afficherUser(nomutilisateur);
    }

    @PostMapping("changerMotDePasse")
    public ResponseEntity<?> changerMotdepasse(@Param("email") String email) {
        Utilisateurs utilisateurs = new Utilisateurs();
        utilisateurs = utilisateurRepository.findByEmail(email);
        System.out.println(utilisateurs);
        if (utilisateurs == null) {
            return ResponseEntity.ok(new MessageResponse("L'email n'existe pas!!!!!"));
        }
        return utilisateurService.resetMotdepasse(utilisateurs);
    }

    @PostMapping("updateMotdepasse")
    public ResponseEntity<?> updateMotdepasse(@RequestBody HashMap<String, String> request) {
       String email = request.get("email");
        System.out.println(email + " emaillllllllllllllllllllllllllllllllllllllllll");
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return new ResponseEntity<>("Utilisateur non fourni!", HttpStatus.BAD_REQUEST);
        }
        String currentPassword = request.get("currentpassword");
        String newPassword = request.get("newpassword");
        System.out.println(newPassword + " newpassworddddddddddddddddddddddddddddddddddddddddddddd");
        String confirmpassword = request.get("confirmpassword");
        System.out.println(currentPassword + "  currentpassword");
        if (!newPassword.equals(confirmpassword)) {
            return new ResponseEntity<>("PasswordNotMatched", HttpStatus.BAD_REQUEST);
        }
        String userPassword = user.getPassword();
        System.out.println(userPassword + "currentpassword encrypt");

        System.out.println("ça passe sur le premier if");
        try {
            System.out.println("ça passe sur le premier try");

            if (newPassword != null && !newPassword.isEmpty() && !StringUtils.isEmpty(newPassword)) {
                System.out.println(newPassword + "newpassworddddddddddddddddddddddddddddddddddddddddddddd1111111111111111111111111");

                if (bCryptPasswordEncoder.matches(currentPassword, userPassword)) {
                    System.out.println(newPassword + "newpassworddddddddddddddddddddddddddddddddddddddddddddd222222222222222222");
                    utilisateurService.updateMotdepasse(user, newPassword);
                }
            } else {
                return new ResponseEntity<>("IncorrectCurrentPassword", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>("Mot de passe changé avec succès!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error Occured: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/afficherAllUtilisateurs")
    public SignupResponse1 afficherAllUtilisateurs(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "2", required = false) int pageSize) {
        return utilisateurService.afficherAllUtilisateurs(pageNo, pageSize);
    }

    @GetMapping("/afficherAllUtilisateur")
    public List<Utilisateurs> afficherAllStructureStatus() {
        System.out.println("jhvbeiuvmbbbbbbbbbkekkkkkkkkkkkkkkkkkkkkkkk");
        List<Utilisateurs> utilisateursList = utilisateurRepository.findAll();

        System.out.println("jhvbeiuvmbbbbbbbbbkekkkkkkkkkkkkkkkkkkkkkkk");
        ArrayList<List<Utilisateurs>> utilisateursListfinal = new ArrayList<>();
        System.out.println(utilisateursList + "  jhvbeiuvmbbbbbbbbbkekkkkkkkkkkkkkkkkkkkkkkk");

        utilisateursListfinal.add(utilisateursList);

        return utilisateursList;
    }

    @GetMapping("afficherpreference")
    public List<Activites> getAllPreference() {
        return activitesRepository.findAll();
    }


    @GetMapping("afficherutilisateurparid/{id}")
    public Utilisateurs afficherutilisateurparid(@PathVariable Long id) {
        return utilisateurRepository.findByIduser(id);
    }
    @DeleteMapping("supprimeruser/{iduser}")
    public ResponseEntity<?> supprimeruser(@PathVariable Long iduser){
        User user=userRepository.findByIduser(iduser);
        if(utilisateurRepository.findByIduser(iduser)!=null){
            ProfileUtilisateurs profileUtilisateurs=profileUtilisateurRepository.findByUtilisateurs(utilisateurRepository.findByIduser(iduser));
           if (profileUtilisateurs!=null){
               profileUtilisateurRepository.delete(profileUtilisateurs);
           }
        }
        if(structureRepository.findByIduser(iduser)!=null){
            ProfileStructure profileStructure=profileStructureRepository.findByStructure(structureRepository.findByIduser(iduser));
            if(profileStructure!=null){
                profileStructureRepository.delete(profileStructure);
            }
        }
        if(structureRepository.findByIduser(iduser)!=null){
            List<Abonnement> abonnement=abonnementRepository.findByStructure(structureRepository.findByIduser(iduser));
            if (abonnement!=null){
                for (Abonnement abonnement1:
                        abonnement) {
                    abonnementRepository.delete(abonnement1);
                }
            }
        }
        if (utilisateurRepository.findByIduser(iduser)!=null){
            List<Abonnement> abonnement=abonnementRepository.findByUtilisateurs(utilisateurRepository.findByIduser(iduser));
            if (abonnement!=null){
                for (Abonnement abonnement1:
                        abonnement) {
                    abonnementRepository.delete(abonnement1);
                }
            }
        }

        if (utilisateurRepository.findByIduser(iduser)!=null){
            List<Demande> demande=demandeRepository.findByUtilisateurs(utilisateurRepository.findByIduser(iduser));
            if (demande!=null){
                for (Demande demande1:
                        demande) {
                    demandeRepository.delete(demande1);
                }
            }
        }
        userRepository.delete(user);
        return ResponseEntity.ok(new MessageResponse("Utilisateur supprimer avec success!")) ;
    }

    @GetMapping("afficherstructureparid/{id}")
    public Structure afficherstructureparid(@PathVariable Long id) {
        return structureRepository.findByIduser(id);
    }


}


