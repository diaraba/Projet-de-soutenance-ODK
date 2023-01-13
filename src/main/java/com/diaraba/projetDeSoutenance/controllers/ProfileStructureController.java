package com.diaraba.projetDeSoutenance.controllers;

import com.diaraba.projetDeSoutenance.models.ProfileStructure;
import com.diaraba.projetDeSoutenance.security.services.StructureService;
import com.diaraba.projetDeSoutenance.security.services.profilestructure.ProfileStructureService;
import com.diaraba.projetDeSoutenance.utilis.ConfigImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/profilestructure")
public class ProfileStructureController {
    @Autowired
    ProfileStructureService profileStructureService;
    @Autowired
    StructureService structureService;
    @PostMapping("/creerProfileStructure")
    public ResponseEntity<?> creerProfileStructure(@Param("activite") String activite,
                                                   @Param("localisation") String localisation,
                                                   @Param("description") String description,
                                                   @Param("slogan") String slogan,
                                                   @Param("numero") String numero,
                                                   @Param("structure") String structure,
                                                   @Param("image") MultipartFile image ) throws IOException {
        ProfileStructure profileStructure=new ProfileStructure();

        String img = StringUtils.cleanPath(image.getOriginalFilename());
        profileStructure.setImage(img);
        profileStructure.setStructure(structureService.trouverStructureparalias(structure));
        profileStructure.setSlogan(slogan);
        profileStructure.setActivite(activite);
        profileStructure.setNumero(numero);
        profileStructure.setDescription(description);
        profileStructure.setLocalisation(localisation);
        String uploaDir = "C:\\Users\\didiarra\\Bureau\\Projet de soutenance\\src\\main\\resources\\assets\\image";
        ConfigImage.saveimg(uploaDir, img, image);
        
        return profileStructureService.creerProfileStructure(profileStructure);
    }
}
