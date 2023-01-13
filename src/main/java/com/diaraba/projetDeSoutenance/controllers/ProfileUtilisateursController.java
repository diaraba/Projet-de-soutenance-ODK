package com.diaraba.projetDeSoutenance.controllers;

import com.diaraba.projetDeSoutenance.models.ProfileUtilisateurs;
import com.diaraba.projetDeSoutenance.repository.UtilisateurRepository;
import com.diaraba.projetDeSoutenance.security.services.UtilisateurService;
import com.diaraba.projetDeSoutenance.security.services.profileutilisateurs.ProfileUtilisateursService;
import com.diaraba.projetDeSoutenance.utilis.ConfigImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/profileutilisateurs")
public class ProfileUtilisateursController {
    @Autowired
    ProfileUtilisateursService profileUtilisateursService;
    @Autowired
    UtilisateurService utilisateurService;
    @Autowired
    UtilisateurRepository utilisateurRepository;

    @PostMapping("/creerProfileUtilisateurs")
    public ResponseEntity<?> creerProfileUtilisateurs(@Param("nom") String nom,
                                                      @Param("prenom") String prenom,
                                                      @Param("genre") String genre,
                                                      @Param("numero") String numero,
                                                      @Param("situation") String situation,
                                                      @Param("utilisateur") String utilisateur,
                                                      @Param("image") MultipartFile image ) throws IOException {


        ProfileUtilisateurs profileUtilisateurs=new ProfileUtilisateurs();

        String img = StringUtils.cleanPath(image.getOriginalFilename());

        String uploaDir = "C:\\Users\\didiarra\\Bureau\\Projet de soutenance\\src\\main\\resources\\assets\\image";
        ConfigImage.saveimg(uploaDir, img, image);
        profileUtilisateurs.setImage(img);
        profileUtilisateurs.setNom(nom);
        profileUtilisateurs.setPrenom(prenom);
        profileUtilisateurs.setGenre(genre);
        profileUtilisateurs.setNumero(numero);
        profileUtilisateurs.setSituation(situation);
        profileUtilisateurs.setUtilisateurs(utilisateurRepository.findByNomutilisateur(utilisateur));


        return profileUtilisateursService.creerProfileUtilisateurs(profileUtilisateurs);
    }
}
