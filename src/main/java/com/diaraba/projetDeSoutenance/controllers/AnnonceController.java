package com.diaraba.projetDeSoutenance.controllers;

import com.diaraba.projetDeSoutenance.models.Annonce;
import com.diaraba.projetDeSoutenance.security.services.StructureService;
import com.diaraba.projetDeSoutenance.security.services.annonce.AnnonceService;
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
import java.util.Date;

@RestController
@RequestMapping("/api/annonce")
public class AnnonceController {

    @Autowired
    AnnonceService annonceService;
    @Autowired
    StructureService structureService;
    @PostMapping("/creerAnnonce")
    public ResponseEntity<?> creerAnnonce(@Param("titre") String titre,
                                          @Param("contenu") String contenu,
                                          @Param("objet") String objet,
                                          @Param("structure") String structure,
                                          @Param("image") MultipartFile image) throws IOException {

        Annonce annonce=new Annonce();
        String img = StringUtils.cleanPath(image.getOriginalFilename());
        annonce.setImage(img);
        String uploaDir = "C:\\Users\\didiarra\\Bureau\\Projet de soutenance\\src\\main\\resources\\assets\\image";
        ConfigImage.saveimg(uploaDir, img, image);

        annonce.setDate(new Date());
        annonce.setObjet(objet);
        annonce.setStructure(structureService.trouverStructureparalias(structure));
        annonce.setContenu(contenu);
        annonce.setTitre(titre);
        return annonceService.creerAnnonce(annonce);
    }

}
