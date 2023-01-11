package com.diaraba.projetDeSoutenance.controllers;

import com.diaraba.projetDeSoutenance.models.ProfileStructure;
import com.diaraba.projetDeSoutenance.security.services.profilestructure.ProfileStructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/profilestructure")
public class ProfileStructureController {
    @Autowired
    ProfileStructureService profileStructureService;
    @PostMapping
    public ResponseEntity<?> creerProfileStructure(@Param("activite") String activite,
                                                   @Param("localisation") String localisation,
                                                   @Param("description") String description,
                                                   @Param("slogan") String slogan,
                                                   @Param("numero") String numero,
                                                   @Param("structure") String structure,
                                                   @Param("image") MultipartFile image ){
        ProfileStructure profileStructure=new ProfileStructure();
        
        return profileStructureService.creerProfileStructure(profileStructure);
    }
}
