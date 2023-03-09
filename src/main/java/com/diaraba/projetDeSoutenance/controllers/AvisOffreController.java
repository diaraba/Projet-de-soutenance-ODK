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
    @Autowired
    private TypeOffreRepository typeOffreRepository;

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
        avis.setAvistype(typeOffre);
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
        notification.setIdstructure(structure);
        notification.setIdtarget(avis.getIdavisoffre());
        notification.setStatus("true");
        notification.setEtat("false");
        notification.setTarget("avis");
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

    @PutMapping("/modifierAvisOffre/{id}/{structure}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @Param("titre") String titre,
                                    @Param("description") String description,
                                    @Param("cible") String cible,
                                    @Param("conditions") String conditions,
                                    @PathVariable Long structure,
                                    @Param("typeOffre") String typeOffre,
                                    @Param("image") MultipartFile image) throws IOException {

        TypeOffre typeOffre1 = new TypeOffre();
        Structure structure1 = new Structure();

        System.out.println(titre + "titreeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
        System.out.println(conditions + "conditionnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn");
        System.out.println(description + "descriptionnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn");
        System.out.println(cible + "cibleeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");


        structure1 = structureRepository.findByIduser(structure);
        AvisOffre avis = new AvisOffre();


        AvisOffre currentavisoffre = avisOffreRepository.findByIdavisoffre(id);
        if (image == null) {
            String img = currentavisoffre.getImage();
            avis.setImage(img);
        } else {
            String img = StringUtils.cleanPath(image.getOriginalFilename());
            String uploaDir = IMAGE_PATH;
            ConfigImage.saveimg(uploaDir, img, image);
            avis.setImage(img);
        }

        if (titre == null) {
            System.out.println("higyfutugyfklkcytcy" + currentavisoffre.getTitre());
            avis.setTitre(currentavisoffre.getTitre());
        } else if (titre.trim().isEmpty()) {
            avis.setTitre(currentavisoffre.getTitre());
        } else {
            avis.setTitre(titre);
        }

        if (description == null) {
            System.out.println("higyfutugyfklkcytcy" + currentavisoffre.getDescription());
            avis.setDescription(currentavisoffre.getDescription());
        } else if (description.trim().isEmpty()) {
            avis.setDescription(currentavisoffre.getDescription());
        } else {
            avis.setDescription(description);
        }

        if (cible == null) {
            System.out.println("higyfutugyfklkcytcy" + currentavisoffre.getCible());
            avis.setCible(currentavisoffre.getCible());
        } else if (cible.trim().isEmpty()) {
            avis.setCible(currentavisoffre.getCible());
        } else {
            avis.setCible(cible);
        }


        if (conditions == null) {
            System.out.println("higyfutugyfklkcytcy" + currentavisoffre.getConditions());
            avis.setConditions(currentavisoffre.getConditions());
        } else if (description.trim().isEmpty()) {
            avis.setConditions(currentavisoffre.getConditions());
        } else {
            avis.setConditions(conditions);
        }


        if (typeOffre == null) {
            System.out.println("higyfutugyfklkcytcy" + currentavisoffre.getTypeOffre());
            avis.setTypeOffre(currentavisoffre.getTypeOffre());
        } else if (typeOffre.trim().isEmpty()) {
            avis.setTypeOffre(currentavisoffre.getTypeOffre());
        } else {
            typeOffre1 = typeOffreService.trouverTypeOffreParNom(typeOffre);
            avis.setTypeOffre(typeOffre1);
            avis.setAvistype(typeOffre);
        }
        avis.setDate(new Date());


        avis.setStructure(structure1);
        return avisOffreService.updateAvisOffre(id, avis);
    }

    @PostMapping("creerTypeOffre")
    public ResponseEntity<?> creerTypeOffre(@Param("nom") String nom) {
        TypeOffre typeOffre = new TypeOffre();
        typeOffre.setNom(nom);
        return typeOffreService.creerTypeOffre(typeOffre);
    }

    @GetMapping("affichertypeoffre")
    public List<TypeOffre> affichertypeoffre() {
        return typeOffreRepository.findAll();
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
    @DeleteMapping("supprimeravisOffre/{idavisoffre}")
    public ResponseEntity<?> supprimeravisOffre(@PathVariable Long idavisoffre){
        AvisOffre avisOffre=avisOffreRepository.findByIdavisoffre(idavisoffre);
        avisOffreRepository.delete(avisOffre);
        return ResponseEntity.ok(new MessageResponse("Annonce supprimer avec success!")) ;
    }

}
