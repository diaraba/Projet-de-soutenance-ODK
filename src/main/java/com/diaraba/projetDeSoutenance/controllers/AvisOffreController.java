package com.diaraba.projetDeSoutenance.controllers;


import com.diaraba.projetDeSoutenance.models.*;
import com.diaraba.projetDeSoutenance.payload.response.AvisOffreResponse;
import com.diaraba.projetDeSoutenance.payload.response.MessageResponse;
import com.diaraba.projetDeSoutenance.repository.*;
import com.diaraba.projetDeSoutenance.security.services.StructureService;
import com.diaraba.projetDeSoutenance.security.services.avisOffre.AvisOffreService;
import com.diaraba.projetDeSoutenance.security.services.typeOffre.TypeOffreService;
import com.diaraba.projetDeSoutenance.utilis.ConfigImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import static com.diaraba.projetDeSoutenance.utilis.constants.IMAGE_PATH;

@CrossOrigin(origins = "*", maxAge = 3600)
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
    @Autowired
    AvisOffreRepository avisOffreRepository;
    @Autowired
    private AbonnementRepository abonnementRepository;
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @PostMapping("/creerAvisoffre/{structure}")
    public ResponseEntity<?> creerAvisoffre(@PathVariable Long structure,
                                            @Param("titre") String titre,
                                            @Param("description") String description,
                                            @Param("cible") String cible,
                                            @Param("conditions") String conditions,
                                            @Param("typeOffre") String typeOffre,
                                            @Param("image") MultipartFile image) throws IOException {

        TypeOffre typeOffre1 = new TypeOffre();
        Structure structure1 = new Structure();


        typeOffre1 = typeOffreService.trouverTypeOffreParNom(typeOffre);


        structure1 = structureRepository.findByIduser(structure);
        AvisOffre avis = new AvisOffre();
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
        String uploaDir = IMAGE_PATH;
        ConfigImage.saveimg(uploaDir, img, image);
        avis = avisOffreService.creerAvisOffre(avis);
        //Notification
        Notification notification = new Notification();
        notification.setStatus("true");
        notification.setEtat("false");
        notification.setTitre(structure1.getAlias());
        notification.setContenu(" Cher utilisateur votre structure " + structure1.getAlias() + " vient de d'ajouter un nouvel avis de " + typeOffre);
        Notification notification1 = notificationRepository.save(notification);

        for (Abonnement abonnement :
                abonnementRepository.findByStructure(structure1)) {
            System.out.println(abonnement + "abonnementttttttttttttttttttttttt");
            abonnement.getUtilisateurs().getNotifications().add(notification1);

            utilisateurRepository.save(abonnement.getUtilisateurs());
        }
        return ResponseEntity.ok(new MessageResponse("AvisOffre créer avec success!"));
    }

    @PutMapping("/modifierAvisOffre/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @Param("titre") String titre,
                                    @Param("description") String description,
                                    @Param("cible") String cible,
                                    @Param("conditions") String conditions,
                                    @Param("structure") String structure,
                                    @Param("typeOffre") String typeOffre,
                                    @Param("image") MultipartFile image) throws IOException {

        TypeOffre typeOffre1 = new TypeOffre();
        Structure structure1 = new Structure();


        typeOffre1 = typeOffreService.trouverTypeOffreParNom(typeOffre);


        structure1 = structureService.trouverStructureparalias(structure);
        AvisOffre avis = new AvisOffre();
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
        String uploaDir = IMAGE_PATH;
        ConfigImage.saveimg(uploaDir, img, image);
        return avisOffreService.updateAvisOffre(id, avis);
    }

    @PostMapping("creerTypeOffre")
    public ResponseEntity<?> creerTypeOffre(@Param("nom") String nom) {
        TypeOffre typeOffre = new TypeOffre();
        typeOffre.setNom(nom);
        return typeOffreService.creerTypeOffre(typeOffre);
    }

    @GetMapping("/afficherAllAvisOffre")
    public AvisOffreResponse afficherAllAvisOffre(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "2", required = false) int pageSize) {
        return avisOffreService.afficherAllAvisOffre(pageNo, pageSize);
    }

    @GetMapping("afficheravisparidstructure/{id}")
    public List<AvisOffre> afficheravisparidstructure(@PathVariable Long id) {
        Structure structure = new Structure();
        structure = structureRepository.findByIduser(id);
        return avisOffreRepository.findByStructure(structure);
    }

    @GetMapping("afficheravisoffreparid/{id}")
    public AvisOffre afficheravisoffreparid(@PathVariable Long id) {
        return avisOffreRepository.findByIdavisoffre(id);
    }


}
