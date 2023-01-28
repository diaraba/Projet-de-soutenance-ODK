package com.diaraba.projetDeSoutenance.controllers;

import com.diaraba.projetDeSoutenance.models.Activites;
import com.diaraba.projetDeSoutenance.models.Structure;
import com.diaraba.projetDeSoutenance.models.Utilisateurs;
import com.diaraba.projetDeSoutenance.payload.response.MessageResponse;
import com.diaraba.projetDeSoutenance.payload.response.SignupResponse1;
import com.diaraba.projetDeSoutenance.payload.response.StructureResponse;
import com.diaraba.projetDeSoutenance.repository.ActivitesRepository;
import com.diaraba.projetDeSoutenance.repository.StructureRepository;
import com.diaraba.projetDeSoutenance.repository.UtilisateurRepository;
import com.diaraba.projetDeSoutenance.security.services.UtilisateurService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("afficherUtilisateur/{nomutilisateur}")
    public Utilisateurs afficherutilisateur(@PathVariable("nomutilisateur")String nomutilisateur){

        return utilisateurService.afficherUser(nomutilisateur);
    }
    @PostMapping("changerMotDePasse")
    public ResponseEntity<?> changerMotdepasse(@Param("email")String email){
        Utilisateurs utilisateurs=new Utilisateurs();
        utilisateurs=utilisateurRepository.findByEmail(email);
        System.out.println(utilisateurs);
        if(utilisateurs==null)
        {
           return ResponseEntity.ok(new MessageResponse("L'email n'existe pas!!!!!")) ;
        }
        return utilisateurService.resetMotdepasse(utilisateurs);
    }

    @PostMapping("updateMotdepasse")
    public ResponseEntity<?> updateMotdepasse(@RequestBody HashMap<String, String> request){
            String email = request.get("email");
            Utilisateurs user = utilisateurRepository.findByEmail(email);
            if (user == null) {
                return new ResponseEntity<>("Utilisateur non fourni!", HttpStatus.BAD_REQUEST);
            }
            String currentPassword = request.get("currentpassword");
            String newPassword = request.get("newpassword");
            String confirmpassword = request.get("confirmpassword");
            if (!newPassword.equals(confirmpassword)) {
                return new ResponseEntity<>("PasswordNotMatched", HttpStatus.BAD_REQUEST);
            }
            String userPassword = user.getPassword();
            try {
                if (newPassword != null && !newPassword.isEmpty() && !StringUtils.isEmpty(newPassword)) {
                    if (bCryptPasswordEncoder.matches(currentPassword, userPassword)) {
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
            @RequestParam(value = "pageNo", defaultValue = "0", required = false)int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "2", required = false)int pageSize)
    {
        return utilisateurService.afficherAllUtilisateurs(pageNo, pageSize);
    }
    @GetMapping("afficherpreference")
    public List<Activites> getAllPreference(){
        return activitesRepository.findAll();
    }


    @GetMapping("afficherutilisateurparid/{id}")
    public Utilisateurs afficherutilisateurparid(@PathVariable Long id){
        return utilisateurRepository.findByIduser(id);
    }
    @GetMapping("afficherstructureparid/{id}")
    public Structure afficherstructureparid(@PathVariable Long id){
        return structureRepository.findByIduser(id);
    }
}


