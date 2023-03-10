package com.diaraba.projetDeSoutenance.controllers;

import com.diaraba.projetDeSoutenance.models.ProfileUtilisateurs;
import com.diaraba.projetDeSoutenance.models.Utilisateurs;
import com.diaraba.projetDeSoutenance.repository.ProfileUtilisateurRepository;
import com.diaraba.projetDeSoutenance.repository.UtilisateurRepository;
import com.diaraba.projetDeSoutenance.security.services.UtilisateurService;
import com.diaraba.projetDeSoutenance.security.services.profileutilisateurs.ProfileUtilisateursService;
import com.diaraba.projetDeSoutenance.utilis.ConfigImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.diaraba.projetDeSoutenance.utilis.constants.IMAGE_PATH;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/profileutilisateurs")
public class ProfileUtilisateursController {
    @Autowired
    ProfileUtilisateursService profileUtilisateursService;
    @Autowired
    UtilisateurService utilisateurService;
    @Autowired
    UtilisateurRepository utilisateurRepository;
    @Autowired
    ProfileUtilisateurRepository profileUtilisateurRepository;

    @PostMapping("/creerProfileUtilisateurs/{utilisateur}")
    public ResponseEntity<?> creerProfileUtilisateurs(@PathVariable Long utilisateur,
                                                      @Param("nom") String nom,
                                                      @Param("prenom") String prenom,
                                                      @Param("genre") String genre,
                                                      @Param("numero") String numero,
                                                      @Param("situation") String situation,
                                                      @Param("etat") String etat,
                                                      @Param("image") MultipartFile image) throws IOException {


        ProfileUtilisateurs profileUtilisateurs=new ProfileUtilisateurs();


        String img = StringUtils.cleanPath(image.getOriginalFilename());
        System.out.println(nom);
        System.out.println(nom);
        System.out.println(prenom);
        System.out.println(genre);
        System.out.println(etat);
        System.out.println(numero);
        System.out.println(situation);
        System.out.println(image);


        String uploaDir =IMAGE_PATH;
        ConfigImage.saveimg(uploaDir, img, image);
        profileUtilisateurs.setImage(img);
        profileUtilisateurs.setNom(nom);
        profileUtilisateurs.setPrenom(prenom);
        profileUtilisateurs.setGenre(genre);
        if (etat==null){
            profileUtilisateurs.setEtat("true");
        }else{
            profileUtilisateurs.setEtat(etat);
        }

        profileUtilisateurs.setNumero(numero);
        profileUtilisateurs.setSituation(situation);
        profileUtilisateurs.setUtilisateurs(utilisateurRepository.findByIduser(utilisateur));

        System.out.println(profileUtilisateurs);
        return profileUtilisateursService.creerProfileUtilisateurs(profileUtilisateurs);
    }
    @CrossOrigin(origins = "http://localhost:8100")
    @PutMapping("/modifierProfileUtilisateur/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@Param("nom") String nom,
                                    @Param("prenom") String prenom,
                                    @Param("genre") String genre,
                                    @Param("numero") String numero,
                                    @Param("situation") String situation,
                                    @Param("etat") String etat,
                                    @Param("image") MultipartFile image
                                    ) throws IOException {

        System.out.println(nom);
        System.out.println(prenom);
        System.out.println(genre);
        System.out.println(numero +"dddddddddddddddddddd");
        System.out.println(situation+"dddddddddddddddddddd");
        System.out.println(etat);
        System.out.println(image+"nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn");
        ProfileUtilisateurs profileUtilisateurs;
        Utilisateurs utilisateurs=utilisateurRepository.findByIduser(id);



        profileUtilisateurs=profileUtilisateurRepository.findByUtilisateurs(utilisateurs);

        ProfileUtilisateurs currentprofile=new ProfileUtilisateurs();

        System.out.println(profileUtilisateurs);
        if(nom==null) {
            System.out.println(profileUtilisateurs.getNom());
            currentprofile.setNom(profileUtilisateurs.getNom());
        } else if ( nom.trim().isEmpty()) {
            currentprofile.setNom(profileUtilisateurs.getNom());
        } else {
            System.out.println(nom);
            currentprofile.setNom(nom);
        }

        if(prenom==null) {
            currentprofile.setPrenom(profileUtilisateurs.getPrenom());
        } else if (prenom.trim().isEmpty()) {
            currentprofile.setPrenom(profileUtilisateurs.getPrenom());
        } else {
            currentprofile.setPrenom(prenom);
        }

        if(genre==null) {
            currentprofile.setGenre(profileUtilisateurs.getGenre());
        }else if (genre.trim().isEmpty()){
            currentprofile.setGenre(profileUtilisateurs.getGenre());
        } else currentprofile.setGenre(genre);

        if(situation==null) {
            System.out.println("higyfutugyfklkcytcy"+profileUtilisateurs.getSituation());
            currentprofile.setSituation(profileUtilisateurs.getSituation());
        } else if ( situation.trim().isEmpty() ) {
            currentprofile.setSituation(profileUtilisateurs.getSituation());
        } else {
            currentprofile.setSituation(situation);

        }

        if(numero==null ) {
            currentprofile.setNumero(profileUtilisateurs.getNumero());
        } else if ( numero.trim().isEmpty()) {
            currentprofile.setNumero(profileUtilisateurs.getNumero());
        } else {
            currentprofile.setNumero(numero);
        }

        if(etat==null) {
            currentprofile.setEtat(profileUtilisateurs.getEtat());
        }else if(etat.trim().isEmpty()){
            currentprofile.setEtat(profileUtilisateurs.getEtat());
        }
        else {
            currentprofile.setEtat(etat);
        }

        if(image==null ) {
            String img =profileUtilisateurs.getImage();
            currentprofile.setImage(img);
        }else {
            String img = StringUtils.cleanPath(image.getOriginalFilename());
            String uploaDir =IMAGE_PATH;
            ConfigImage.saveimg(uploaDir, img, image);
            currentprofile.setImage(img);
        }

        currentprofile.setUtilisateurs(profileUtilisateurs.getUtilisateurs());



        return profileUtilisateursService.updateProfileUtilisateurs(profileUtilisateurs.getIdutilisateur(),currentprofile);
    }
    @GetMapping("afficherprofile/{id}")
    public ProfileUtilisateurs afficherprofile(@PathVariable Long id){
        ProfileUtilisateurs profile= new ProfileUtilisateurs();
        Utilisateurs utilisateurs=new Utilisateurs();
        utilisateurs=utilisateurRepository.findByIduser(id);
        profile=profileUtilisateurRepository.findByUtilisateurs(utilisateurs);
        return profile;
    }
}
