package com.diaraba.projetDeSoutenance.controllers;


import com.diaraba.projetDeSoutenance.models.*;
import com.diaraba.projetDeSoutenance.repository.StructureRepository;
import com.diaraba.projetDeSoutenance.repository.TypeOffreRepository;
import com.diaraba.projetDeSoutenance.security.services.StructureService;
import com.diaraba.projetDeSoutenance.security.services.avisOffre.AvisOffreService;
import com.diaraba.projetDeSoutenance.security.services.typeOffre.TypeOffreService;
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
import java.util.*;

@RestController
@RequestMapping("/api/avisoffre")
public class AvisOffreController {
    @Autowired
    AvisOffreService avisOffreService;

    @Autowired
    StructureRepository structureRepository;
    @Autowired
    StructureService structureService;
    @Autowired
    TypeOffreService typeOffreService;

    @PostMapping("/creerAvisoffre")
    public ResponseEntity<?> creerAvisoffre(@Param("titre") String titre,
                                            @Param("description") String description,
                                            @Param("cible") String cible,
                                            @Param("conditions") String conditions,
                                            @Param("structure") String structure,
                                            @Param("typeOffre") String typeOffre,
                                            @Param("image") MultipartFile image) throws IOException {

        TypeOffre typeOffre1= new TypeOffre();
      Structure structure1= new Structure();


       typeOffre1=typeOffreService.trouverTypeOffreParNom(typeOffre);


      structure1=structureService.trouverStructureparalias(structure);
        AvisOffre avis= new AvisOffre();
        avis.setCible(cible);
        avis.setDate(new Date());
        avis.setDescription(description);
        avis.setConditions(conditions);
        avis.setTitre(titre);
        avis.setTypeOffre(typeOffre1);
        avis.setStructure(structure1);
System.out.println(structure1);
System.out.println(typeOffre1);
        String img = StringUtils.cleanPath(image.getOriginalFilename());
        avis.setImage(img);
        String uploaDir = "C:\\Users\\didiarra\\Bureau\\Projet de soutenance\\src\\main\\resources\\assets\\image";
        ConfigImage.saveimg(uploaDir, img, image);
        return avisOffreService.creerStructure(avis);
    }

    @PostMapping("creerTypeOffre")
    public ResponseEntity<?> creerTypeOffre(@Param("nom") String nom){
        TypeOffre typeOffre=new TypeOffre();
        typeOffre.setNom(nom);
        return typeOffreService.creerTypeOffre(typeOffre);
    }
}
