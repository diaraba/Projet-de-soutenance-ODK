package com.diaraba.projetDeSoutenance.controllers;

import com.diaraba.projetDeSoutenance.models.Annonce;
import com.diaraba.projetDeSoutenance.payload.response.AnnonceResponse;
import com.diaraba.projetDeSoutenance.payload.response.AvisOffreResponse;
import com.diaraba.projetDeSoutenance.security.services.StructureService;
import com.diaraba.projetDeSoutenance.security.services.annonce.AnnonceService;
import com.diaraba.projetDeSoutenance.utilis.ConfigImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
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
        String uploaDir = "C:\\Users\\Ash Born\\Desktop\\Projet de soutenance\\src\\main\\resources\\assets\\image";
        ConfigImage.saveimg(uploaDir, img, image);

        annonce.setDate(new Date());
        annonce.setObjet(objet);
        annonce.setStructure(structureService.trouverStructureparalias(structure));
        annonce.setContenu(contenu);
        annonce.setTitre(titre);
        return annonceService.creerAnnonce(annonce);
    }

    @PutMapping("/modifierAnnonce/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @Param("titre") String titre,
                                    @Param("contenu") String contenu,
                                    @Param("objet") String objet,
                                    @Param("structure") String structure,
                                    @Param("image") MultipartFile image) throws IOException {

        Annonce annonce=new Annonce();
        String img = StringUtils.cleanPath(image.getOriginalFilename());
        annonce.setImage(img);
        String uploaDir = "C:\\Users\\Ash Born\\Desktop\\Projet de soutenance\\src\\main\\resources\\assets\\image";
        ConfigImage.saveimg(uploaDir, img, image);

        annonce.setDate(new Date());
        annonce.setObjet(objet);
        annonce.setStructure(structureService.trouverStructureparalias(structure));
        annonce.setContenu(contenu);
        annonce.setTitre(titre);
        return annonceService.updateAnnonce(id,annonce);
    }

    @GetMapping("/afficherAllAnnonce")
    public AnnonceResponse afficherAllAnnonce(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false)int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "2", required = false)int pageSize)
    {
        return annonceService.afficherAnnonce(pageNo,pageSize);
    }

}
