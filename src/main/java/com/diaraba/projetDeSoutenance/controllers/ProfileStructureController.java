package com.diaraba.projetDeSoutenance.controllers;

import com.diaraba.projetDeSoutenance.models.ProfileStructure;
import com.diaraba.projetDeSoutenance.models.Structure;
import com.diaraba.projetDeSoutenance.repository.ProfileStructureRepository;
import com.diaraba.projetDeSoutenance.repository.StatutRepository;
import com.diaraba.projetDeSoutenance.repository.StructureRepository;
import com.diaraba.projetDeSoutenance.security.services.StructureService;
import com.diaraba.projetDeSoutenance.security.services.profilestructure.ProfileStructureService;
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
@RequestMapping("/api/profilestructure")
public class ProfileStructureController {
    @Autowired
    ProfileStructureService profileStructureService;
    @Autowired
    ProfileStructureRepository profileStructureRepository;
    @Autowired
    StructureService structureService;
    @Autowired
    StructureRepository structureRepository;
    @Autowired
    private StatutRepository statutRepository;

    @PostMapping("/creerProfileStructure/{structure}")
    public ResponseEntity<?> creerProfileStructure(@PathVariable  Long structure,
                                                   @Param("activite") String activite,
                                                   @Param("localisation") String localisation,
                                                   @Param("description") String description,
                                                   @Param("slogan") String slogan,
                                                   @Param("numero") String numero,
                                                   @Param("nom") String nom,
                                                   @Param("image") MultipartFile image ) throws IOException {
        System.out.println(nom);
        System.out.println(activite);
        System.out.println(localisation);
        System.out.println(numero);
        System.out.println(slogan);
        System.out.println(description);
        System.out.println(image+"bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
        ProfileStructure profileStructure=new ProfileStructure();

        String img = StringUtils.cleanPath(image.getOriginalFilename());
        profileStructure.setImage(img);
        profileStructure.setStructure(structureRepository.findByIduser(structure));
        profileStructure.setSlogan(slogan);
        profileStructure.setActivite(activite);
        profileStructure.setNumero(numero);
        profileStructure.setDescription(description);
        profileStructure.setLocalisation(localisation);
        profileStructure.setNom(nom);
        String uploaDir = IMAGE_PATH;
        ConfigImage.saveimg(uploaDir, img, image);
        
        return profileStructureService.creerProfileStructure(profileStructure);
    }

    @PutMapping("/modifierProfileStructure/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @Param("activite") String activite,
                                    @Param("localisation") String localisation,
                                    @Param("description") String description,
                                    @Param("slogan") String slogan,
                                    @Param("numero") String numero,
                                    @Param("image") MultipartFile image,
                                    @Param("nom") String nom) throws IOException {

           ProfileStructure currentprofile=new ProfileStructure();
           Structure structure=structureRepository.findByIduser(id);
           System.out.println(structure+"structure object ");
           ProfileStructure profileStructure=profileStructureRepository.findByStructure(structure);
           System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::"+nom + ":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::"+localisation + ":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::"+slogan + ":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::"+description + ":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::"+activite + ":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::"+numero + ":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        currentprofile.setStructure(structure);
        if(nom==null) {
            System.out.println(profileStructure.getNom()+"nom first");
            currentprofile.setNom(profileStructure.getNom());
        } else if ( nom.trim().isEmpty()) {
            currentprofile.setNom(profileStructure.getNom());
        } else {
            System.out.println(nom+"second");
            currentprofile.setNom(nom);
        }


        if(activite==null) {
            currentprofile.setActivite(profileStructure.getActivite());
        } else if (activite.trim().isEmpty()) {
            currentprofile.setActivite(profileStructure.getActivite());
        } else {
            currentprofile.setActivite(activite);
        }


        if(localisation==null) {
            currentprofile.setLocalisation(profileStructure.getLocalisation());
        } else if (localisation.trim().isEmpty()) {
            currentprofile.setLocalisation(profileStructure.getLocalisation());
        } else {
            currentprofile.setLocalisation(localisation);
        }

        if(description==null) {
            currentprofile.setDescription(profileStructure.getDescription());
        }else if (description.trim().isEmpty()){
            currentprofile.setDescription(profileStructure.getDescription());
        } else currentprofile.setDescription(description);

        if(slogan==null) {
            System.out.println("higyfutugyfklkcytcy"+profileStructure.getSlogan());
            currentprofile.setSlogan(profileStructure.getSlogan());
        } else if ( slogan.trim().isEmpty() ) {
            currentprofile.setSlogan(profileStructure.getSlogan());
        } else {
            currentprofile.setSlogan(slogan);

        }

        if(numero==null ) {
            currentprofile.setNumero(profileStructure.getNumero());
        } else if ( numero.trim().isEmpty()) {
            currentprofile.setNumero(profileStructure.getNumero());
        } else {
            currentprofile.setNumero(numero);
        }

        if(image==null ) {
            String img =profileStructure.getImage();
            currentprofile.setImage(img);
        }else {
            String img = StringUtils.cleanPath(image.getOriginalFilename());
            String uploaDir =IMAGE_PATH;
            ConfigImage.saveimg(uploaDir, img, image);
            currentprofile.setImage(img);
        }

        return profileStructureService.updateProfileStructure(profileStructure.getId(), currentprofile);
    }

    @GetMapping("afficherprofil/{id}")
    public ProfileStructure afficherprofilstructure(@PathVariable Long id){
        ProfileStructure profile=new ProfileStructure();
        Structure structure= new Structure();
        structure=structureRepository.findByIduser(id);
        profile=profileStructureRepository.findByStructure(structure);
        return  profile;
    }
}
